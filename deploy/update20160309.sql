-----20160309 Begin
----��־���޸��ֶ����ƣ���Ϊ����ֶβ�ֻ�Ǳ���ISARPID��ͬʱҲ�ᱣ��SECTIONID��ACTIONID�ȣ��޸ĳ�TARGETID��׼ȷ
alter table e_iosa_operation_log  rename column ISARPID to TARGETID;
comment on column e_iosa_operation_log.TARGETID is '��־��Ӧ�ļ����ҵ��ID';
---����ֶ�type������������־����
alter table e_iosa_operation_log add type varchar(40);
comment on column e_iosa_operation_log.type is '��־��������:isarp��action��section��report';
--����ֶ�oper_type����������������ͣ���ˣ�������ơ����桢�ύ�ȣ�
alter table e_iosa_operation_log add oper_type varchar(40);
comment on column e_iosa_operation_log.oper_type is '��־��Ӧ�Ĳ�������';
-----20160309 End----
