CREATE TABLE ticket.industrial_parties(
    id int AUTO_INCREMENT PRIMARY KEY,
    ticket_name varchar(50) not null,
    first_level varchar(500),
    third_level varchar(500),
    direction varchar(500),
    production varchar(500),
    docking_person varchar(500),
    position varchar(255),
    market_time varchar(255)
)

CREATE TABLE ticket.project_process(
    id int AUTO_INCREMENT PRIMARY KEY,
    project_name varchar(200),
    first_level varchar(500),
    sec_level varchar(500),
    business varchar(500),
    start_time date,
    progress varchar(255),
    financing_share varchar(255),
    financier varchar(255),
    business_unit varchar(255),
    lp varchar(255)
)

CREATE TABLE ticket.company_info(
    id int AUTO_INCREMENT PRIMARY KEY,
    ticket_code varchar(50),
    ticket_name varchar(100),
    total_market_value double,
    circulation_value double,
    status varchar(50),
    listed_date varchar(50),
    province varchar(100),
    third_level varchar(500),
    business varchar(500),
    prod_name varchar(500),
    prod_type varchar(500),
    funds double,
    trading_financial_assets double,
    asset_liability_ratio double,
    operating_income double,
    net_profit double,
    comp_type varchar(100),
    address varchar(100),
    office varchar(100),
    city varchar(100),
    county_level_city varchar(100),
    tel varchar(50),
    email varchar(50)
)