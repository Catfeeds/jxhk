-- Alter table
--�۲����  �� ARR_TIME��DEP_TIME��number��ת��ΪString����
alter table L_OBSERVE_ACTIVITY
add (ARR_TIME_STR VARCHAR2(10),
	 DEP_TIME_STR VARCHAR2(10));
update L_OBSERVE_ACTIVITY t 
set t.ARR_TIME_STR = nvl2(t.ARR_TIME,to_char(t.ARR_TIME,'0000'),''),
    t.DEP_TIME_STR = nvl2(t.DEP_TIME,to_char(t.DEP_TIME,'0000'),'');
alter table L_OBSERVE_ACTIVITY
drop (ARR_TIME, DEP_TIME);
alter table L_OBSERVE_ACTIVITY rename column ARR_TIME_STR to ARR_TIME;
alter table L_OBSERVE_ACTIVITY rename column DEP_TIME_STR to DEP_TIME;
