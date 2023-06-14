DROP TABLE IF EXISTS scenery_category;
CREATE TABLE scenery_category
(
    `id`          INT(32)     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        VARCHAR(50) NOT NULL UNIQUE COMMENT '分类名称',
    `parent_id`   INT(32)     NOT NULL COMMENT '父级id',
    `sort`        INT(10)     NOT NULL COMMENT '排序',
    `is_del`      VARCHAR(1)  NOT NULL DEFAULT 1 COMMENT '是否删除;0-未删除 1-已删除',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '分类';

DROP TABLE IF EXISTS player_technical_feature;
CREATE TABLE player_technical_feature
(
    `id`           INT(32)   NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `feature`      VARCHAR(255) COMMENT '特点',
    `super_strong` VARCHAR(255) COMMENT '超强',
    `strong`       VARCHAR(255) COMMENT '强项',
    `weak`         VARCHAR(255) COMMENT '弱项',
    `create_time`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '球员技术特点表';

DROP TABLE IF EXISTS player_detail;
CREATE TABLE player_detail
(
    `id`                      INT(32)   NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `cross`                   VARCHAR(255) COMMENT '传中',
    `shoot`                   VARCHAR(255) COMMENT '射门',
    `header`                  VARCHAR(255) COMMENT '头球',
    `short_pass`              VARCHAR(255) COMMENT '短传',
    `volley_shot`             VARCHAR(255) COMMENT '凌空',
    `dribbling`               VARCHAR(255) COMMENT '盘带',
    `banana_kick`             VARCHAR(255) COMMENT '弧线',
    `free_kick`               VARCHAR(255) COMMENT '任意球',
    `long_pass`               VARCHAR(255) COMMENT '长传',
    `ball_control`            VARCHAR(255) COMMENT '控球',
    `speed_up`                VARCHAR(255) COMMENT '加速',
    `speed`                   VARCHAR(255) COMMENT '速度',
    `agility`                 VARCHAR(255) COMMENT '敏捷',
    `reaction`                VARCHAR(255) COMMENT '反应',
    `balance`                 VARCHAR(255) COMMENT '平衡',
    `shot_power`              VARCHAR(255) COMMENT '射门力量',
    `bounce`                  VARCHAR(255) COMMENT '弹跳',
    `stamina`                 VARCHAR(255) COMMENT '耐力',
    `strong`                  VARCHAR(255) COMMENT '强壮',
    `long_shot`               VARCHAR(255) COMMENT '远射',
    `aggressiveness`          VARCHAR(255) COMMENT '侵略性',
    `intercept_consciousness` VARCHAR(255) COMMENT '拦截意识',
    `positioning`             VARCHAR(255) COMMENT '跑位',
    `view`                    VARCHAR(255) COMMENT '视野',
    `penalty_kick`            VARCHAR(255) COMMENT '点球',
    `marking`                 VARCHAR(255) COMMENT '盯人',
    `steal`                   VARCHAR(255) COMMENT '抢断',
    `slide_tackle`            VARCHAR(255) COMMENT '铲球',
    `gk_fish_dive`            VARCHAR(255) COMMENT '鱼跃',
    `gk_handle_shape`         VARCHAR(255) COMMENT '手型',
    `gk_kick_off`             VARCHAR(255) COMMENT '开球',
    `gk_stance`               VARCHAR(255) COMMENT '站位',
    `gk_reaction`             VARCHAR(255) COMMENT '反应',
    `CF`                      VARCHAR(255) COMMENT '中锋',
    `SF`                      VARCHAR(255) COMMENT '影锋',
    `LW`                      VARCHAR(255) COMMENT '左边锋',
    `RW`                      VARCHAR(255) COMMENT '右边锋',
    `AMF`                     VARCHAR(255) COMMENT '前腰',
    `CM`                      VARCHAR(255) COMMENT '中前卫',
    `LM`                      VARCHAR(255) COMMENT '左中场',
    `RM`                      VARCHAR(255) COMMENT '右中场',
    `CDM`                     VARCHAR(255) COMMENT '后腰',
    `LWB`                     VARCHAR(255) COMMENT '左翼卫',
    `RWB`                     VARCHAR(255) COMMENT '右翼卫',
    `CB`                      VARCHAR(255) COMMENT '中后卫',
    `LB`                      VARCHAR(255) COMMENT '左后卫',
    `RB`                      VARCHAR(255) COMMENT '右后卫',
    `GK`                      VARCHAR(255) COMMENT '守门员',
    `create_time`             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '球员详细信息表;';

DROP TABLE IF EXISTS player_info;
CREATE TABLE player_info
(
    `id`                       INT(32)     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `detail_id`                INT(32)     NOT NULL COMMENT '详细信息表对应id',
    `technical_feature_id`     INT(32)     NOT NULL COMMENT '技术特点表对应id',
    `c_name`                   VARCHAR(64) NOT NULL COMMENT '中文名称',
    `e_name`                   VARCHAR(128) COMMENT '英文名称',
    `country_id`               INT(32) COMMENT '所属国家id',
    `team_id`                  INT(32) COMMENT '所属俱乐部id',
    `height`                   VARCHAR(4) COMMENT '身高',
    `weight`                   VARCHAR(64) COMMENT '体重',
    `price`                    VARCHAR(10) COMMENT '身价;单位：欧元',
    `team_num`                 VARCHAR(32) COMMENT '俱乐部号码',
    `position`                 VARCHAR(2) COMMENT '位置;1-前锋 2-中场 3-后卫 4-门将 11-主教练 12-助理教练 13-老板 14-主席 15-足球总监 16-球队经理',
    `foot`                     VARCHAR(1) COMMENT '惯用脚;1-左脚 2-右脚 3-左右脚',
    `birthday`                 DATETIME COMMENT '生日',
    `nickname`                 VARCHAR(64) COMMENT '绰号',
    `contract_expiration`      VARCHAR(1)           DEFAULT 1 COMMENT '合同到期;1-俱乐部 2-国家队',
    `comprehensive_abilities`  VARCHAR(255) COMMENT '综合能力',
    `speed`                    VARCHAR(255) COMMENT '速度',
    `shoot`                    VARCHAR(255) COMMENT '射门',
    `pass`                     VARCHAR(255) COMMENT '传球',
    `dribbling`                VARCHAR(255) COMMENT '盘带',
    `defend`                   VARCHAR(255) COMMENT '防守',
    `power`                    VARCHAR(255) COMMENT '力量',
    `international_reputation` VARCHAR(255) COMMENT '国际声望',
    `weak_foot`                VARCHAR(255) COMMENT '逆足能力',
    `skill_move`               VARCHAR(255) COMMENT '花式技巧',
    `person_type`              VARCHAR(1) COMMENT '人员类型;1-球员 2-教练 3-工作人员',
    `is_del`                   VARCHAR(1)  NOT NULL DEFAULT 0 COMMENT '是否删除;0-未删除 1-已删除',
    `create_time`              TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`              TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (detail_id) references player_detail (id)
        on update cascade
        on delete cascade,
    foreign key (technical_feature_id) references player_technical_feature (id)
        on update cascade
        on delete cascade
) COMMENT = '球员基本信息表';

DROP TABLE IF EXISTS player_transfer_record;
CREATE TABLE player_transfer_record
(
    `id`            INT           NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `player_id`     INT           NOT NULL COMMENT '所属球员id',
    `time`          DATETIME(255) NOT NULL COMMENT '转会时间',
    `price`         VARCHAR(255) COMMENT '转会价格',
    `out_team_id`   INT           NOT NULL COMMENT '转出球队id',
    `enter_team_id` INT           NOT NULL COMMENT '转入球队id',
    `transfer_mode` VARCHAR(1) COMMENT '转会方式;1-免签 2-租借 3-租借回归 4-现金',
    `create_time`   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (player_id) references player_info (id)
        on update cascade
        on delete cascade
) COMMENT = '球员转会记录表';

DROP TABLE IF EXISTS player_honor_record;
CREATE TABLE player_honor_record
(
    `id`          INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `player_id`   INT       NOT NULL COMMENT '所属球员id',
    `honor_id`    INT COMMENT '荣誉id',
    `time`        VARCHAR(255) COMMENT '获得时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (player_id) references player_info (id)
        on update cascade
        on delete cascade
) COMMENT = '球员荣誉记录表';

DROP TABLE IF EXISTS player_game_stats;
CREATE TABLE player_game_stats
(
    `id`                      INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `player_id`               INT       NOT NULL COMMENT '所属球员id',
    `team_id`                 INT       NOT NULL COMMENT '所属球队id',
    `game_id`                 INT       NOT NULL COMMENT '所属比赛id',
    `score`                   DECIMAL(2, 1) COMMENT '评分',
    `is_starter`              VARCHAR(1)         DEFAULT 1 COMMENT '是否首发;1-是 2-否',
    `playing_time`            INT COMMENT '出场时间',
    `goal`                    INT COMMENT '进球',
    `assists`                 INT COMMENT '助攻',
    `red_card`                INT COMMENT '红牌',
    `yellow_card`             INT COMMENT '黄牌',
    `total_pass`              INT COMMENT '传球总数',
    `precise_pass`            INT COMMENT '精准传球',
    `key_pass`                INT COMMENT '关键传球',
    `successful_through_ball` INT COMMENT '成功直塞',
    `successful_cross`        INT COMMENT '成功传中',
    `successful_long_pass`    INT COMMENT '成功长传',
    `shoot`                   INT COMMENT '射门',
    `shot_on_target`          INT COMMENT '射正',
    `shoot_aside`             INT COMMENT '射偏',
    `be_blocked`              INT COMMENT '被封堵',
    `dribble`                 INT COMMENT '过人',
    `successful_dribble`      INT COMMENT '成功过人',
    `be_fouled`               INT COMMENT '被犯规',
    `heading_duel`            INT COMMENT '争顶',
    `successful_heading_duel` INT COMMENT '成功争顶',
    `steal`                   INT COMMENT '抢断',
    `clearance_kick`          INT COMMENT '解围',
    `intercept`               INT COMMENT '拦截',
    `foul`                    INT COMMENT '犯规',
    `loss_of_possession`      INT COMMENT '丢失球权',
    `be_pass`                 INT COMMENT '被过',
    `offside`                 INT COMMENT '越位',
    `fighting`                INT COMMENT '扑救',
    `create_time`             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (player_id) references player_info (id)
        on update cascade
        on delete cascade
) COMMENT = '球员比赛数据表';



DROP TABLE IF EXISTS team_info;
CREATE TABLE team_info
(
    `id`          INT         NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `c_name`      VARCHAR(64) NOT NULL COMMENT '中文名称',
    `e_name`      VARCHAR(128) COMMENT '英文名称',
    `set_up_time` VARCHAR(4) COMMENT '成立时间',
    `country_id`  INT         NOT NULL COMMENT '所属国家id',
    `city_id`     INT         NOT NULL COMMENT '所属城市id',
    `captain_id`  INT COMMENT '队长id',
    `home_court`  VARCHAR(64) COMMENT '主场',
    `accommodate` VARCHAR(10) COMMENT '容纳',
    `tel`         VARCHAR(32) COMMENT '电话',
    `email`       VARCHAR(64) COMMENT '邮箱',
    `address`     VARCHAR(255) COMMENT '地址',
    `team_logo`   VARCHAR(255) COMMENT '队徽',
    `nikename`    VARCHAR(64) COMMENT '绰号',
    `is_club`     VARCHAR(1)  NOT NULL DEFAULT 1 COMMENT '是否俱乐部;1-俱乐部 2-国家队',
    `is_del`      VARCHAR(1)  NOT NULL DEFAULT 0 COMMENT '是否删除;0-未删除 1-已删除',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '球队基本信息表';

DROP TABLE IF EXISTS team_coach_history;
CREATE TABLE team_coach_history
(
    `id`          INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `team_id`     INT       NOT NULL COMMENT '球队id',
    `player_id`   INT       NOT NULL COMMENT '球员id',
    `win`         INT COMMENT '胜',
    `flat`        INT COMMENT '平',
    `lose`        INT COMMENT '负',
    `start_time`  DATETIME  NOT NULL COMMENT '开始执教时间',
    `end_time`    DATETIME  NOT NULL COMMENT '结束执教时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (team_id) references team_info (id)
        on update cascade
        on delete cascade
) COMMENT = '历任主教练表';

DROP TABLE IF EXISTS team_record;
CREATE TABLE team_record
(
    `id`          INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `team_id`     INT       NOT NULL COMMENT '所属球队id',
    `player_id`   INT       NOT NULL COMMENT '所属球员id',
    `goal`        INT COMMENT '进球',
    `appearance`  INT COMMENT '出场',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (team_id) references team_info (id)
        on update cascade
        on delete cascade
) COMMENT = '队史纪录表';

DROP TABLE IF EXISTS team_honor_record;
CREATE TABLE team_honor_record
(
    `id`          INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `team_id`     INT       NOT NULL COMMENT '所属球队id',
    `honor_id`    INT COMMENT '荣誉id',
    `time`        VARCHAR(255) COMMENT '获得时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (team_id) references team_info (id)
        on update cascade
        on delete cascade
) COMMENT = '球队荣誉记录表';

DROP TABLE IF EXISTS team_ranking_history;
CREATE TABLE team_ranking_history
(
    `id`          INT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `team_id`     INT COMMENT '球队id',
    `ranking`     INT COMMENT '排名',
    `time`        VARCHAR(255) COMMENT '时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    foreign key (team_id) references team_info (id)
        on update cascade
        on delete cascade
) COMMENT = '球队联赛历史排名表';
