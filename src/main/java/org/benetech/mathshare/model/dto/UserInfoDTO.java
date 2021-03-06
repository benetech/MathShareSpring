package org.benetech.mathshare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private Integer id;

    private String email;

    private String userType;

    private List<String> grades;

    private Integer notifyForMobile;

    private String role;

    private Integer infoVersion;

    private String disability;

    private String gender;

    private Integer yearOfBirth;

    private String county;

    private String zipcode;

    private String grade;
}
