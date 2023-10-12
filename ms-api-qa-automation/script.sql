create table test_results
(
    id serial
        constraint test_result_pk
            primary key,
    run_time varchar(255) not null,
    service_url varchar(255) not null,
    request_method_type varchar(255) not null,
    result_status_code int,
    result_status varchar(255),
    json_response_token varchar(6000),
    json_request_token varchar(6000)
);

create unique index test_result_service_url_uindex
    on test_result (service_url);