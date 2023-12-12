/*
-- prompt Dropping ATTACHMENT_FILE...
drop table ATTACHMENT_FILE cascade;
-- prompt Dropping ATTACH_TYPE...
drop table ATTACH_TYPE cascade;
-- prompt Dropping RES_TYPE...
drop table RES_TYPE cascade;
-- prompt Dropping RES_INFO...
drop table RES_INFO cascade;
-- prompt Dropping PERIOD_INFO...
drop table PERIOD_INFO cascade;
-- prompt Dropping AUC_TYPE...
drop table AUC_TYPE cascade;
-- prompt Dropping AUC_INFO...
drop table AUC_INFO cascade;
-- prompt Dropping CUST_INFO...
drop table CUST_INFO cascade;
-- prompt Dropping PROJ_INFO...
drop table PROJ_INFO cascade;
-- prompt Dropping PROJ_TYPE...
drop table PROJ_TYPE cascade;
-- prompt Dropping PROPOSAL_INFO...
drop table PROPOSAL_INFO cascade;
-- prompt Dropping ATTACH_PROPOSAL...
drop table ATTACH_PROPOSAL cascade;
*/

-- prompt Creating ATTACHMENT_FILE...
create table ATTACHMENT_FILE
(
  attachment_id NUMERIC not null,
  description   VARCHAR(255),
  file_name     VARCHAR(255),
  file_path     VARCHAR(255),
  create_date   DATE not null
)
;
alter table ATTACHMENT_FILE
  add constraint PK_ATTCHMENT_FILE primary key (ATTACHMENT_ID);

-- prompt Creating ATTACH_TYPE...
create table ATTACH_TYPE
(
  attachment_type           VARCHAR(50) not null,
  description               VARCHAR(100) not null,
  application_category_type VARCHAR(20) not null
)
;
alter table ATTACH_TYPE
  add constraint PK_ATTACH_TYPE primary key (ATTACHMENT_TYPE);

-- prompt Creating RES_TYPE...
create table RES_TYPE
(
  resource_type      VARCHAR(20) not null,
  resource_type_desc VARCHAR(50) not null
)
;
alter table RES_TYPE
  add constraint PK_RES_TYPE primary key (RESOURCE_TYPE);

-- prompt Creating RES_INFO...
create table RES_INFO
(
  resource_id   NUMERIC(9) not null,
  resource_name VARCHAR(100),
  resource_type VARCHAR(20)
)
;
alter table RES_INFO
  add constraint PK_RES_INFO primary key (RESOURCE_ID);
alter table RES_INFO
  add constraint FK_RES_TYPE foreign key (RESOURCE_TYPE)
  references RES_TYPE (RESOURCE_TYPE);

-- prompt Creating PERIOD_INFO...
create table PERIOD_INFO
(
  period_id        NUMERIC(9) not null,
  period_type      VARCHAR(20) not null,
  description      VARCHAR(50) not null,
  begin_date       DATE not null,
  end_date         DATE not null,
  parent_period_id NUMERIC(9)
)
;
alter table PERIOD_INFO
  add constraint PK_PERIOD_INFO primary key (PERIOD_ID);

-- prompt Creating AUC_TYPE...
create table AUC_TYPE
(
  auction_type VARCHAR(20) not null,
  description  VARCHAR(50) not null
)
;
alter table AUC_TYPE
  add constraint PK_AUC_TYPE primary key (AUCTION_TYPE);

-- prompt Creating AUC_INFO...
create table AUC_INFO
(
  auction_id           NUMERIC(9) not null,
  commitment_period_id NUMERIC(9) not null,
  auction_period_id    NUMERIC(9) not null,
  auction_begin_date   DATE not null,
  auction_end_date     DATE not null,
  auction_type         VARCHAR(20) not null
)
;
alter table AUC_INFO
  add constraint PK_AUC_INFO primary key (AUCTION_ID);
alter table AUC_INFO
  add constraint FK_AUC_PERIOD foreign key (AUCTION_PERIOD_ID)
  references PERIOD_INFO (PERIOD_ID);
alter table AUC_INFO
  add constraint FK_AUC_TYPE foreign key (AUCTION_TYPE)
  references AUC_TYPE (AUCTION_TYPE);
alter table AUC_INFO
  add constraint FK_CP_INFO foreign key (COMMITMENT_PERIOD_ID)
  references PERIOD_INFO (PERIOD_ID);

-- prompt Creating CUST_INFO...
create table CUST_INFO
(
  customer_id   NUMERIC(9) not null,
  customer_name VARCHAR(30) not null
)
;
alter table CUST_INFO
  add constraint PK_CUST_INFO primary key (CUSTOMER_ID);

-- prompt Creating PROJ_INFO...
create table PROJ_INFO
(
  project_id   NUMERIC(9) not null,
  project_name VARCHAR(100)
)
;
alter table PROJ_INFO
  add constraint PK_PROJ_INFO primary key (PROJECT_ID);

-- prompt Creating PROJ_TYPE...
create table PROJ_TYPE
(
  project_type      VARCHAR(50) not null,
  project_type_desc VARCHAR(100) not null
)
;
alter table PROJ_TYPE
  add constraint PK_PROJ_TYPE primary key (PROJECT_TYPE);

-- prompt Creating PROPOSAL_INFO...
create table PROPOSAL_INFO
(
  proposal_id    NUMERIC(9) not null,
  proposal_label VARCHAR(104),
  project_id     NUMERIC(9) not null,
  project_type   VARCHAR(50) not null,
  resource_id    NUMERIC(9) not null,
  customer_id    NUMERIC(9) not null,
  auction_id     NUMERIC(9) not null,
  period_id      NUMERIC(9) not null
)
;
alter table PROPOSAL_INFO
  add constraint PK_PROPOSAL_INFO primary key (PROPOSAL_ID);
alter table PROPOSAL_INFO
  add constraint FK_AUC_INFO foreign key (AUCTION_ID)
  references AUC_INFO (AUCTION_ID);
alter table PROPOSAL_INFO
  add constraint FK_CUST_INFO foreign key (CUSTOMER_ID)
  references CUST_INFO (CUSTOMER_ID);
alter table PROPOSAL_INFO
  add constraint FK_PERIOD_INFO foreign key (PERIOD_ID)
  references PERIOD_INFO (PERIOD_ID);
alter table PROPOSAL_INFO
  add constraint FK_PROJ_INFO foreign key (PROJECT_ID)
  references PROJ_INFO (PROJECT_ID);
alter table PROPOSAL_INFO
  add constraint FK_PROJ_TYPE foreign key (PROJECT_TYPE)
  references PROJ_TYPE (PROJECT_TYPE);
alter table PROPOSAL_INFO
  add constraint FK_RES_INFO foreign key (RESOURCE_ID)
  references RES_INFO (RESOURCE_ID);

-- prompt Creating ATTACH_PROPOSAL...
create table ATTACH_PROPOSAL
(
  proposal_id     NUMERIC(9) not null,
  attachment_id   NUMERIC not null,
  attachment_type VARCHAR(50) not null
)
;
alter table ATTACH_PROPOSAL
  add constraint PK_ATTACH_PROPOSAL primary key (PROPOSAL_ID, ATTACHMENT_ID);
alter table ATTACH_PROPOSAL
  add constraint FK_ATTACH_FILE foreign key (ATTACHMENT_ID)
  references ATTACHMENT_FILE (ATTACHMENT_ID);
alter table ATTACH_PROPOSAL
  add constraint FK_ATTACH_TYPE foreign key (ATTACHMENT_TYPE)
  references ATTACH_TYPE (ATTACHMENT_TYPE);
alter table ATTACH_PROPOSAL
  add constraint FK_PROPOSAL_INFO foreign key (PROPOSAL_ID)
  references PROPOSAL_INFO (PROPOSAL_ID);

-- prompt Loading ATTACHMENT_FILE...
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1001, 'Project documents', 'Project docs.zip', '/FCTS_data/Attachments', to_date('25-02-2008 15:16:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1002, 'meeting notes', 'Follow-up from meeting.msg', '/FCTS_data/Attachments', to_date('29-10-2008 20:10:01', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1003, 'Resource_info', 'Resource_info1.pdf', '/FCTS_data/Attachments', to_date('26-11-2008 20:41:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1004, 'Resource QDN', 'Resource_qdn.pdf', '/FCTS_data/Attachments', to_date('26-11-2008 20:41:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1005, null, 'dr_project_description.pdf', '/FCTS_data/Attachments', to_date('28-04-2018 03:18:45', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1006, 'DR Project Description', 'dr_project_description2.pdf', '/FCTS_data/Attachments', to_date('06-06-2018 20:49:21', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1007, 'Sponsored Policy Resource Certification', 'Sponsored Policy Resource.pdf', '/FCTS_data/Attachments', to_date('20-06-2018 22:00:54', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1008, 'Funding Plan', 'Funding Plan .xlsm', '/FCTS_data/Attachments', to_date('22-06-2018 02:19:26', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1009, 'customer acquisition aggregate', 'custacquisition_SR Aggregation.pdf', '/FCTS_data/Attachments', to_date('22-06-2018 03:54:36', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1010, 'ISO-NE Offer Resource Trigger Price Challenge Document ', 'ISO-NE Offer Resource Trigger Price Challenge Document.xlsm', '/FCTS_data/Attachments', to_date('22-06-2018 05:19:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1011, 'ISO-NE Offer Resource Trigger Price Challenge Document ', 'ISO-NE Offer Resource Trigger Price Challenge Document FINAL.xlsm', '/FCTS_data/Attachments', to_date('22-06-2018 05:34:14', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1012, 'M and V Plan', 'M+V Plan Document.pdf', '/FCTS_data/Attachments', to_date('22-06-2018 05:58:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1013, 'All Resources Info', 'All_resources_info.zip', '/FCTS_data/Attachments', to_date('22-06-2018 06:49:32', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1014, 'Contract Info', 'Export Contract.docx', '/FCTS_data/Attachments', to_date('22-06-2018 13:55:03', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1015, null, 'Proof of Ownership.docx', '/FCTS_data/Attachments', to_date('22-06-2018 13:55:16', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1016, null, 'External Resource Documentation.zip', '/FCTS_data/Attachments', to_date('22-06-2018 13:55:28', 'dd-mm-yyyy hh24:mi:ss'));
insert into ATTACHMENT_FILE (attachment_id, description, file_name, file_path, create_date)
values (1017, 'System Load and Capacity Projections', 'System Load and Capacity Projections.zip', '/FCTS_data/Attachments', to_date('22-06-2018 13:55:38', 'dd-mm-yyyy hh24:mi:ss'));
commit;
-- prompt 17 records loaded
-- prompt Loading ATTACH_TYPE...
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('FLOOROFFERPRICE.REVIEWREQ.COSTJUST', 'Cost Justification', 'PROPOSAL.FOP.REVREQ');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('PROPOSAL.OTHERS', 'Other', 'PROPOSAL');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('PROPOSAL.QDN', 'New Capacity Proposal Qualification Determination Notification', 'QDN');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('PROPOSAL.SA.SPR.CERT.DOC', 'SA Sponsored Policy Resource Certification', 'PROPOSAL.SUB.AUC');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.DR.CUST_ACQ_PLAN', 'Customer Acquisition Plan', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.DR.FUNDINGSOURCE', 'Source of Funding Plan', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.DR.MV_PLAN', 'Measurement and Verification (M&V) Plan', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.DR.MV_PLAN_SUPPORT', 'Measurement and Verification Plan Supporting Information', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.DR.PROJECT_DESCRIPTION', 'Project Description', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.IMPORT.EXPORT_CONTRACT', 'Export Contract', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.IMPORT.EXT_RESOURCE', 'External Resource Documentation', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.IMPORT.OWNERSHIP', 'Proof of Ownership', 'PROPOSAL.QP');
insert into ATTACH_TYPE (attachment_type, description, application_category_type)
values ('QP.IMPORT.SYS_LOAD_PROJECTIONS', 'System Load and Capacity Projections', 'PROPOSAL.QP');
commit;
-- prompt 13 records loaded
-- prompt Loading RES_TYPE...
insert into RES_TYPE (resource_type, resource_type_desc)
values ('GEN', 'Generator');
insert into RES_TYPE (resource_type, resource_type_desc)
values ('IMPORT', 'Import');
insert into RES_TYPE (resource_type, resource_type_desc)
values ('DR', 'Demand Resource');
commit;
-- prompt 3 records loaded
-- prompt Loading RES_INFO...
insert into RES_INFO (resource_id, resource_name, resource_type)
values (500, 'Solar Aggregation ', 'DR');
insert into RES_INFO (resource_id, resource_name, resource_type)
values (100, 'Big Generator', 'GEN');
insert into RES_INFO (resource_id, resource_name, resource_type)
values (300, 'Solar Generator', 'GEN');
insert into RES_INFO (resource_id, resource_name, resource_type)
values (200, 'NEMA Demand Resource', 'DR');
insert into RES_INFO (resource_id, resource_name, resource_type)
values (400, 'NB Import', 'IMPORT');
commit;
-- prompt 5 records loaded
-- prompt Loading PERIOD_INFO...
insert into PERIOD_INFO (period_id, period_type, description, begin_date, end_date, parent_period_id)
values (1, 'COMMITMENT', '2010-11', to_date('01-06-2010 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2011 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PERIOD_INFO (period_id, period_type, description, begin_date, end_date, parent_period_id)
values (3, 'COMMITMENT', '2012-13', to_date('01-06-2012 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2013 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PERIOD_INFO (period_id, period_type, description, begin_date, end_date, parent_period_id)
values (11, 'COMMITMENT', '2020-21', to_date('01-06-2020 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2021 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into PERIOD_INFO (period_id, period_type, description, begin_date, end_date, parent_period_id)
values (13, 'COMMITMENT', '2022-23', to_date('01-06-2022 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2023 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), null);
commit;
-- prompt 4 records loaded
-- prompt Loading AUC_TYPE...
insert into AUC_TYPE (auction_type, description)
values ('FCA', 'FCA');
insert into AUC_TYPE (auction_type, description)
values ('RCA2', 'RCA2');
insert into AUC_TYPE (auction_type, description)
values ('ARA1', 'First Annual Reconfiguration Auction');
insert into AUC_TYPE (auction_type, description)
values ('ARA2', 'Second Annual Reconfiguration Auction');
insert into AUC_TYPE (auction_type, description)
values ('ARA3', 'Third Annual Reconfiguration Auction');
insert into AUC_TYPE (auction_type, description)
values ('MRA', 'Monthly Reconfiguration Auction');
insert into AUC_TYPE (auction_type, description)
values ('ANNUAL', 'Annual Bilateral Period');
insert into AUC_TYPE (auction_type, description)
values ('ANNUAL_MONTHLY', 'Annual Bilateral Period');
insert into AUC_TYPE (auction_type, description)
values ('MONTHLY', 'Monthly Bilateral Period');
commit;
-- prompt 9 records loaded
-- prompt Loading AUC_INFO...
insert into AUC_INFO (auction_id, commitment_period_id, auction_period_id, auction_begin_date, auction_end_date, auction_type)
values (1, 1, 1, to_date('01-06-2010 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2011 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'FCA');
insert into AUC_INFO (auction_id, commitment_period_id, auction_period_id, auction_begin_date, auction_end_date, auction_type)
values (3, 3, 3, to_date('01-06-2012 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2013 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'FCA');
insert into AUC_INFO (auction_id, commitment_period_id, auction_period_id, auction_begin_date, auction_end_date, auction_type)
values (11, 11, 11, to_date('01-06-2020 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2021 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'FCA');
insert into AUC_INFO (auction_id, commitment_period_id, auction_period_id, auction_begin_date, auction_end_date, auction_type)
values (13, 3, 13, to_date('01-06-2022 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-06-2023 04:00:00', 'dd-mm-yyyy hh24:mi:ss'), 'FCA');
commit;
-- prompt 4 records loaded
-- prompt Loading CUST_INFO...
insert into CUST_INFO (customer_id, customer_name)
values (50, 'Sun Rise Power');
insert into CUST_INFO (customer_id, customer_name)
values (30, 'Energy Importer');
insert into CUST_INFO (customer_id, customer_name)
values (20, 'Lightening Power ');
insert into CUST_INFO (customer_id, customer_name)
values (40, 'Good Guy Consulting');
insert into CUST_INFO (customer_id, customer_name)
values (10, 'Constant Energy');
commit;
-- prompt 5 records loaded
-- prompt Loading PROJ_INFO...
insert into PROJ_INFO (project_id, project_name)
values (4000, 'Solar Yes');
insert into PROJ_INFO (project_id, project_name)
values (3000, 'Nice Load Response');
insert into PROJ_INFO (project_id, project_name)
values (2000, 'Gravity Works');
insert into PROJ_INFO (project_id, project_name)
values (6000, 'Additional LR');
insert into PROJ_INFO (project_id, project_name)
values (5000, 'New Contract Year');
commit;
-- prompt 5 records loaded
-- prompt Loading PROJ_TYPE...
insert into PROJ_TYPE (project_type, project_type_desc)
values ('NEW_GEN_GT_20', 'New Generation >= 20 MW');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('NEW_GEN_LT_20', 'New Generation < 20MW');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('NEW_IMPORT', 'New Import');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('INCREASE_ABOVE_20', 'Increase above Threshold');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('REPOWER', 'Repowering');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('ENV_COMP', 'Environmental Upgrade');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('INCREMENTAL', 'Incremental Capacity');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('DERATING', 'Reestablishment');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('SIG_INCREASE', 'Significant Increase');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('NEW_DR', 'New Demand Resource');
insert into PROJ_TYPE (project_type, project_type_desc)
values ('EXISTING_DR', 'Incremental Increase of Existing Demand Resource');
commit;
-- prompt 11 records loaded
-- prompt Loading PROPOSAL_INFO...
insert into PROPOSAL_INFO (proposal_id, proposal_label, project_id, project_type, resource_id, customer_id, auction_id, period_id)
values (111, 'CP 2010-11-FCA', 2000, 'INCREMENTAL', 100, 20, 1, 1);
insert into PROPOSAL_INFO (proposal_id, proposal_label, project_id, project_type, resource_id, customer_id, auction_id, period_id)
values (333, 'CP 2020-21-FCA', 4000, 'NEW_GEN_LT_20', 300, 40, 11, 11);
insert into PROPOSAL_INFO (proposal_id, proposal_label, project_id, project_type, resource_id, customer_id, auction_id, period_id)
values (222, 'CP 2012-13-FCA', 3000, 'NEW_DR', 200, 10, 3, 3);
insert into PROPOSAL_INFO (proposal_id, proposal_label, project_id, project_type, resource_id, customer_id, auction_id, period_id)
values (555, 'CP 2022-23-FCA', 6000, 'NEW_DR', 500, 50, 13, 13);
insert into PROPOSAL_INFO (proposal_id, proposal_label, project_id, project_type, resource_id, customer_id, auction_id, period_id)
values (444, 'CP 2022-23-FCA', 5000, 'NEW_IMPORT', 400, 30, 13, 13);
commit;
-- prompt 5 records loaded
-- prompt Loading ATTACH_PROPOSAL...
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (111, 1001, 'PROPOSAL.OTHERS');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (222, 1002, 'PROPOSAL.OTHERS');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (222, 1003, 'PROPOSAL.QDN');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (222, 1004, 'PROPOSAL.OTHERS');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (333, 1014, 'QP.IMPORT.EXPORT_CONTRACT');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (333, 1015, 'QP.IMPORT.OWNERSHIP');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (333, 1016, 'QP.IMPORT.EXT_RESOURCE');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (333, 1017, 'QP.IMPORT.SYS_LOAD_PROJECTIONS');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (444, 1005, 'QP.DR.PROJECT_DESCRIPTION');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (444, 1006, 'QP.DR.PROJECT_DESCRIPTION');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (444, 1007, 'PROPOSAL.SA.SPR.CERT.DOC');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1008, 'QP.DR.FUNDINGSOURCE');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1009, 'QP.DR.CUST_ACQ_PLAN');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1010, 'FLOOROFFERPRICE.REVIEWREQ.COSTJUST');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1011, 'FLOOROFFERPRICE.REVIEWREQ.COSTJUST');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1012, 'QP.DR.MV_PLAN');
insert into ATTACH_PROPOSAL (proposal_id, attachment_id, attachment_type)
values (555, 1013, 'QP.DR.MV_PLAN_SUPPORT');
commit;
-- prompt 17 records loaded


-- prompt Done
