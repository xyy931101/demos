create table file_info (
    id bigint AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    path VARCHAR(300) NOT NULL,
    length bigint not null ,
    created_by VARCHAR(100),
    updateBy varchar(100),
    parent_id bigint,
    is_folder boolean default false ,
    PRIMARY KEY (id)
);

-- COMMENT ON TABLE file_info IS '文件表,记录文件的位置,创建人,大小等信息';
--
-- COMMENT on column file_info.id is 'ID';
-- COMMENT on column file_info.name is '文件名';
-- COMMENT on column file_info.path is '文件在FastDFS上面的路径';
-- COMMENT on column file_info.length is '文件的大小';
-- COMMENT on column file_info.created_by is '创建人';
-- COMMENT on column file_info.updateBy is '更新人';
-- COMMENT on column file_info.parent_id is '所属父ID,一般用于文件夹的上级跟文件属于那哪个文件夹';
-- COMMENT on column file_info.is_folder is '是否是文件夹';

