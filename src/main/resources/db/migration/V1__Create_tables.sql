create table roles
(
    Id serial primary key,
    Role character varying(20)
);

create table users
(
    Id serial primary key,
    Name character varying(255),
    Email character varying(255),
    Password character varying(255),
    Role_Id integer,
    Phone integer,
    Photo bytea,
    Rating double precision,
    foreign key (Role_Id) references roles (Id)
);

create table passports
(
    Id serial primary key,
    Client_Id integer,
    First_Name character varying(255),
    Last_Name character varying(255),
    Birth_Date date,
    Passport_Number character varying(255),
    Identification_Number character varying(255),
    Issue_Date date,
    Issuing_Authority text,
    Passport_Photo bytea,
    Confirmation boolean,
    foreign key (Client_Id) references users (Id)
);

create table licences
(
    Id serial primary key,
    Insurer_Id integer,
    Insurer_Name character varying(255),
    Address character varying(255),
    Taxpayer_Identification_Number integer,
    Licence_Number character varying(255),
    Issue_Decision_Date date,
    Issue_Decision_Number integer,
    Confirmation boolean,
    foreign key (Insurer_Id) references users (Id)
);
create table reviews
(
    Id serial primary key,
    Review text,
    Grade double precision,
    Client_Id integer,
    Insurer_Id integer,
    foreign key (Client_Id) references users (Id),
    foreign key (Insurer_Id) references users (Id)
);
create table insurance_types
(
    Id serial primary key,
    Insurance_Type text
);

create table offers
(
    Id serial primary key,
    Insurance_Type_Id integer,
    Insurer_Id integer,
    Term integer,
    Cost numeric,
    Description text,
    foreign key (Insurer_Id) references users (Id),
    foreign key (Insurance_Type_Id) references insurance_types (Id)
);
create table contracts
(
    Id serial primary key,
    Contract_Date date,
    Client_Id integer,
    Offer_Id integer,
    Insurer_Confirm_Contract_Status boolean,
    Insurer_Confirm_Payment_Status boolean,
    Client_Incident_Status boolean,
    Insurer_Confirm_Incident_Status boolean,
    Client_Pay_Status boolean,
    foreign key (Client_Id) references users (Id),
    foreign key (Offer_Id) references offers (Id)
);

insert into roles (role) values
                             ('ROLE_ADMIN'),
                             ('ROLE_CLIENT'),
                             ('ROLE_INSURER');

insert into insurance_types (insurance_type) values
                                                 ('compulsory insurance of buildings owned by citizens'),
                                                 ('compulsory third party liability insurance for vehicle owners'),
                                                 ('compulsory insurance of the carrier''s civil liability to passengers'),
                                                 ('compulsory medical insurance of foreign citizens and stateless persons temporarily staying or temporarily residing in the Republic of Belarus'),
                                                 ('compulsory liability insurance of commercial organizations engaged in real estate activities, for damage caused in connection with its implementation'),
                                                 ('compulsory insurance with state support for crops, livestock and poultry'),
                                                 ('compulsory civil liability insurance of temporary (anti-crisis) managers in economic insolvency (bankruptcy) proceedings'),
                                                 ('compulsory insurance of civil liability of legal entities and individual entrepreneurs for harm caused by activities related to the operation of individual objects'),
                                                 ('compulsory insurance of civil liability of the carrier for the carriage of dangerous goods'),
                                                 ('compulsory insurance against industrial accidents and occupational diseases'),
                                                 ('compulsory state insurance (compulsory insurance of life, health and (or) property of citizens provided for by law at the expense of the relevant budget)'),
                                                 ('other types of compulsory insurance determined by laws or acts of the President of the Republic of Belarus'),
                                                 ('voluntary insurance related to life insurance, including: life'),
                                                 ('voluntary insurance related to life insurance, including: supplementary pension'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: against accidents'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: against accidents and illnesses during a trip abroad'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: medical expenses'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: property of a legal entity'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: property of citizens'),
                                                 ('voluntary insurance, not related to life insurance, including: cargo insurance'),
                                                 ('voluntary insurance, not related to life insurance, including insurance of: construction and assembly risks'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: business risk'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: civil liability of motor vehicle owners'),
                                                 ('voluntary insurance not related to life insurance, including insurance: civil liability of aircraft owners'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: civil liability of the carrier and forwarder'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: civil liability of organizations that create an increased danger to others'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: civil liability of the employer for harm caused to the life and health of employees'),
                                                 ('voluntary insurance, not related to life insurance, including insurance: civil liability for harm in connection with the implementation of professional activities'),
                                                 ('voluntary insurance not related to life insurance, including insurance: other types of voluntary insurance not related to life insurance'),
                                                 ('reinsurance');
