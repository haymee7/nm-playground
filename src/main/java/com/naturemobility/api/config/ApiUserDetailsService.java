package com.naturemobility.api.config;

import com.naturemobility.api.dao.ClientDao;
import com.naturemobility.api.dao.ClientRoleDao;
import com.naturemobility.api.dto.ClientDto;
import com.naturemobility.api.dto.ClientRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApiUserDetailsService implements UserDetailsService {

  @Autowired
  private final ClientDao clientDao;

  @Autowired
  private final ClientRoleDao clientRoleDao;

  @Override
  public UserDetails loadUserByUsername(String clientPid) throws UsernameNotFoundException {

    ClientDto clientDto = clientDao.findOne(Integer.parseInt(clientPid));
    List<ClientRoleDto> roles = clientRoleDao.findByClientPid(Integer.parseInt(clientPid));

    return new ApiUserDetails(clientDto, roles);
  }
}
