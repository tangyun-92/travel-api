DROP TABLE IF EXISTS scenery_category;
CREATE TABLE scenery_category
(
    `id`          INT(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    `parent_id`   INT(32) NOT NULL COMMENT '父级id',
    `sort`        INT(10) NOT NULL COMMENT '排序',
    `is_del`      VARCHAR(1)  NOT NULL DEFAULT 1 COMMENT '是否删除;0-未删除 1-已删除',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '分类';
