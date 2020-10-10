-- ===============================
--   用户信息表
-- ===============================
create table sys_user(
  user_id bigint auto_increment comment '用户id',
  username varchar(100) unique not null comment '用户名',
  password varchar(100) not null comment '密码',
  email varchar(100) default '' comment '邮箱',
  mobile varchar(11) default '' comment '手机号码',
  sex char(1) default '0' comment '性别 0:男 1:女 2:未知',
  status char(1) default '0' comment '账号状态 0：启用 1：停用',
  del_flag bit default '0' comment '删除状态 0：存在 1：删除',
  create_time datetime comment '创建时间',
  create_by varchar(100) comment '创建者',
  update_time datetime comment '更新时间',
  update_by varchar(100) comment '更新者',
  login_date datetime comment '最后登录时间',
  login_ip datetime comment '最后登录时间'
);
