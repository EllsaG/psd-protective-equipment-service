insert into low_volt_cables (low_volt_cables_id, cable_type)
values (1, 'ВВГнг 4х16');

insert into protective_equipment (protective_equipment_id, circuit_breaker_type, thermal_release_rated_current,
                                  electromagnetic_release_rated_current, circuit_breaker_rated_current,
                                  low_volt_cables_id_fk)
values (1, 'ВА 47-100', 80, 800, 80, 1);
