package com.example.location.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveDriverLocationRequestDto {

  String driverId;
Double latitude;
Double longitude;

}
