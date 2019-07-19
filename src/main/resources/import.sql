-- 【测试数据，正式上线部署前删除或修改】用户初始化 导入用户
insert into shiro_user (id, name, password, username,regist_time) values(124410708119388110, '测试管理员', '3f3563a10868622503cefa7aa06a4d53', 'demoadmin','2018-11-23 23:22:11');
insert into shiro_user (id, name, password, username,regist_time) values(124410708119388111,'测试用户', '81a141133901436960b6cf3ebd0604cb','demouser', '2018-11-23 23:22:11');

-- 角色初始化 导入角色
insert into shiro_role(id,name,code) values(124410708119388120,'管理员','ADMIN');
insert into shiro_role(id,name,code) values(124410708119388121,'普通用户','USER');

-- 权限初始化 导入权限
insert into shiro_permission(id,name,code,type) values(124410708119388130,'demo新增','demo:save','DATA');
insert into shiro_permission(id,name,code,type) values(124410708119388131,'demo编辑','demo:update','DATA');
insert into shiro_permission(id,name,code,type) values(124410708119388132,'demo删除','demo:delete','DATA');
insert into shiro_permission(id,name,code,type) values(124410708119388133,'前台主页','index:home','MENU');
insert into shiro_permission(id,name,code,type) values(124410708119388134,'admin后台菜单','admin:home','MENU');

-- 【测试数据，正式上线部署前删除或修改】用户角色初始化
insert into r_user_role (user_id, role_id) values (124410708119388110, 124410708119388120);
insert into r_user_role (user_id, role_id) values (124410708119388111, 124410708119388121);

-- 角色权限表
insert into r_role_permission(role_id,permission_id) values(124410708119388120,124410708119388130);
insert into r_role_permission(role_id,permission_id) values(124410708119388120,124410708119388131);
insert into r_role_permission(role_id,permission_id) values(124410708119388120,124410708119388132);
insert into r_role_permission(role_id,permission_id) values(124410708119388120,124410708119388133);
insert into r_role_permission(role_id,permission_id) values(124410708119388120,124410708119388134);

insert into r_role_permission(role_id,permission_id) values(124410708119388121,124410708119388130);
insert into r_role_permission(role_id,permission_id) values(124410708119388121,124410708119388131);
insert into r_role_permission(role_id,permission_id) values(124410708119388121,124410708119388133);











































