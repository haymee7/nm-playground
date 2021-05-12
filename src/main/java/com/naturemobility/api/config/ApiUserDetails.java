package com.naturemobility.api.config;

import com.naturemobility.api.dto.ClientDto;
import com.naturemobility.api.dto.ClientRoleDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class ApiUserDetails extends User {

    private ClientDto clientDto;

    public ApiUserDetails(ClientDto clientDto, List<ClientRoleDto> roles) {
        super(Integer.toString(clientDto.getPid()), "", makeGrantedAuthority(roles));
        this.clientDto = clientDto;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<ClientRoleDto> roles) {
        List<GrantedAuthority> list = new ArrayList<>();

        for (ClientRoleDto role : Optional.ofNullable(roles).orElse(Collections.emptyList())) {
            list.add(new SimpleGrantedAuthority(ApiConstant.ROLE.PREFIX + role.getRoleName()));
        }

        return list;
    }
}
