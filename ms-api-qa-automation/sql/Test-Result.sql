create table test_results
(
    id                           serial       not null
        constraint test_result_pk
            primary key,
    run_time                     varchar(255) not null,
    service_url                  varchar(255) not null,
    request_method_type          varchar(255) not null,
    result_status_code           integer,
    result_status                varchar(255),
    provided_json_response_token varchar(6000),
    expected_json_response_token varchar(6000),
    test_result_status_message   varchar(255)
);

alter table test_results
    owner to postgres;

create unique index test_result_service_url_uindex
    on test_results (service_url);

INSERT INTO public.test_results (id, run_time, service_url, request_method_type, result_status_code, result_status,
                                 provided_json_response_token, expected_json_response_token, test_result_status_message)
VALUES (43, '03.04.2022 18:56:03', 'http://localhost:20000/api/v1/product/category/getAll', 'GET', 200, 'FAIL',
        'eyJyZXR1cm5Db2RlIjoxMTExLCJyZXR1cm5NZXNzYWdlIjoixLDFn2xlbWluaXpCYcWfYXLEsXlsYWdlcsOnZWtsZcWfdGlyaWxtacWfdGlyLiIsInJlc3VsdENvdW50Ijo0LCJyZXR1cm5EYXRhIjpbeyJwa0lkIjoxLCJuYW1lIjoiR2l5aW0ifSx7InBrSWQiOjIsIm5hbWUiOiJTcG9yIn0seyJwa0lkIjozLCJuYW1lIjoiSG9iaSJ9LHsicGtJZCI6NCwibmFtZSI6IlNhbmF0In1dfQ==',
        'eyJyZXR1cm5Db2RlIjoxMTExLCJyZXR1cm5NZXNzYWdlIjoixLDFn2xlbWluaXpCYcWfYXLEsXlsYWdlcsOnZWtsZcWfdGlyaWxtacWfdGlyLiIsInJlc3VsdENvdW50Ijo0LCJyZXR1cm5EYXRhIjpbeyJwa0lkIjoxLCJuYW1lIjoiR2l5aW0ifSx7InBrSWQiOjIsIm5hbWUiOiJTcG9yIn0seyJwa0lkIjozLCJuYW1lIjoiSG9iaSJ9LHsicGtJZCI6NCwibmFtZSI6IlNhbmF0MSJ9XX0=',
        'Data Listesi eşleşmiyor !');