package com.naturemobility.api.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.RealtimeData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class GoogleAnalyticsV3Util {

  private static final String APPLICATION_NAME = "NM Integration APP";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static String KEY_FILE_PATH;

  private static final Logger log = LogManager.getLogger(GoogleAnalyticsV3Util.class);

  @Value("${google-analytics-v3.key-file-path}")
  public void setKeyFilePath(String keyFilePath) { KEY_FILE_PATH = keyFilePath; }


  public static int getActiveUserCnt(String viewId) {
    try {
      Analytics analytics = initializeAnalytic();
      Analytics.Data.Realtime.Get realtimeRequest = analytics.data().realtime()
        .get("ga:" + viewId, "rt:activeUsers");

      RealtimeData realtimeData = realtimeRequest.execute();

      return getActiveUserCnt(realtimeData);
    } catch (Exception e) {
      log.info("-- 구글 애널리틱스 접속자수 조회 실패함..", e);
      return 0;
    }
  }


  /*******  구글 애널리틱스 코드 시작    *******/

  /**
   * Initializes an Analytics service object.
   *
   * @return An authorized Analytics service object.
   * @throws IOException
   * @throws GeneralSecurityException
   */
  private static Analytics initializeAnalytic() throws GeneralSecurityException, IOException {

    System.out.println("-- keyFilePath: " + KEY_FILE_PATH);

    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GoogleCredential credential = GoogleCredential
      .fromStream(new FileInputStream(KEY_FILE_PATH))
      .createScoped(AnalyticsScopes.all());

    // Construct the Analytics service object.
    return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
      .setApplicationName(APPLICATION_NAME).build();
  }

  private static int getActiveUserCnt(RealtimeData realtimeData) {
    if (realtimeData.getTotalResults() > 0) {
      return (realtimeData.getRows().get(0).get(0) != null) ? Integer.parseInt(realtimeData.getRows().get(0).get(0)) : 1;
    } else {
      log.info("-- 구글 애널리틱스 접속자수 조회 실패.. 데이터 읽기 실패");
      return 1;
    }
  }
}
