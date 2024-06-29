create table low_volt_cables
(
    low_volt_cables_id int2 primary key,
    cable_type varchar(255) not null
);

create table protective_equipment
(
    protective_equipment_id int2 primary key,
    circuit_breaker_type varchar(255) not null,
    thermal_release_rated_current float4 not null,
    electromagnetic_release_rated_current float4 not null,
    circuit_breaker_rated_current float4 not null,
    low_volt_cables_id_fk int2 not null,
    FOREIGN KEY (low_volt_cables_id_fk) REFERENCES low_volt_cables(low_volt_cables_id)
);

create table protective_equipment_selection
(
    protective_equipment_selection_id  int2 primary key,
    equipment_rated_current float4 not null,
    equipment_starting_current float4 not null,
    calculated_current_of_thermal_release float4 not null,
    calculated_current_of_electromagnetic_release float4 not null
);