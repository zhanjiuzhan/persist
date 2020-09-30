/** 互助信息表头 */
create table if not exists message_header (
    id bigint unsigned not null comment '信息的唯一标志',
    type tinyint unsigned not null comment '信息的类型',
    mgroup tinyint unsigned not null comment '信息的分组',
    status tinyint unsigned not null comment '信息的状态',
    latitude double not null comment '经度',
    longitude double not null comment '维度',
    createTime timestamp not null default now() comment '信息的创建时间',
    primary key (id),
    index idx_msg_type (type),
    index idx_msg_mgroup (mgroup),
    index idx_msg_status (status),
    index idx_msg_position (latitude, longitude),
    index idx_msg_createTime (createTime)
) engine=innodb default charset=utf8 comment='用于广播的信息表头';

/** 互助信息表头 */
create table if not exists help_message_content (
    id bigint unsigned not null comment '信息的唯一标志',
    title varchar(32) not null comment '消息的标题',
    content varchar(128) not null comment '消息内容',
    detail varchar(512) null default '' comment '详细的内容',
    imgPath text comment '消息图片的地址',
    primary key (id),
    index idx_help_msg_title (title)
) engine=innodb default charset=utf8 comment='及时的互帮信息';
