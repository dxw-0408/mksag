-- =============================================================
-- 校园服务智能助手（校园通）数据库初始化脚本
-- 适用：MySQL 8.x
-- 执行：mysql -u root -p < sql/init.sql
-- =============================================================

CREATE DATABASE IF NOT EXISTS `campus_assistant`
  DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `campus_assistant`;

-- 1. 知识条目（校历 / 规章 / FAQ）
CREATE TABLE IF NOT EXISTS `faq` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `category`    VARCHAR(64)  NOT NULL DEFAULT 'faq' COMMENT '分类: calendar/regulation/faq',
  `question`    VARCHAR(512) NOT NULL COMMENT '问题',
  `answer`      TEXT         NOT NULL COMMENT '答案',
  `source`      VARCHAR(512) DEFAULT NULL COMMENT '来源出处（溯源）',
  `source_url`  VARCHAR(512) DEFAULT NULL COMMENT '来源链接',
  `tags`        VARCHAR(255) DEFAULT NULL COMMENT '标签，逗号分隔',
  `status`      TINYINT      NOT NULL DEFAULT 1 COMMENT '1 启用 0 停用',
  `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_faq_category` (`category`),
  KEY `idx_faq_status` (`status`),
  FULLTEXT KEY `ft_faq_qa` (`question`, `answer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校园知识问答条目';

-- 2. 办事流程
CREATE TABLE IF NOT EXISTS `service_procedure` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(128) NOT NULL COMMENT '流程标题',
  `category`    VARCHAR(64)  DEFAULT NULL COMMENT '分类',
  `description` TEXT         DEFAULT NULL COMMENT '流程说明',
  `sort`        INT          NOT NULL DEFAULT 0 COMMENT '排序',
  `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_proc_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='办事流程';

-- 3. 流程步骤
CREATE TABLE IF NOT EXISTS `procedure_step` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `procedure_id` BIGINT UNSIGNED NOT NULL COMMENT '所属流程',
  `step_no`      INT          NOT NULL DEFAULT 1 COMMENT '步骤序号',
  `title`        VARCHAR(128) NOT NULL COMMENT '步骤标题',
  `description`  TEXT         DEFAULT NULL COMMENT '步骤说明',
  `location`     VARCHAR(255) DEFAULT NULL COMMENT '办理地点',
  `contact`      VARCHAR(255) DEFAULT NULL COMMENT '联系人/电话',
  `tip`          TEXT         DEFAULT NULL COMMENT '提示',
  PRIMARY KEY (`id`),
  KEY `idx_step_procedure` (`procedure_id`),
  CONSTRAINT `fk_step_procedure` FOREIGN KEY (`procedure_id`)
    REFERENCES `service_procedure` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='办事流程步骤';

-- 4. 问答会话
CREATE TABLE IF NOT EXISTS `chat_session` (
  `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `conversation_id` VARCHAR(64)  NOT NULL COMMENT '会话标识',
  `user_id`         BIGINT UNSIGNED DEFAULT NULL COMMENT '用户（预留）',
  `title`           VARCHAR(255) DEFAULT NULL COMMENT '会话标题',
  `created_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_conversation_id` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='问答会话';

-- 5. 问答消息
CREATE TABLE IF NOT EXISTS `chat_message` (
  `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `session_id` BIGINT UNSIGNED NOT NULL COMMENT '所属会话',
  `role`       VARCHAR(16)  NOT NULL DEFAULT 'user' COMMENT 'user/assistant',
  `content`    TEXT         NOT NULL COMMENT '内容',
  `sources`    JSON         DEFAULT NULL COMMENT '来源引用',
  `created_at` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_msg_session` (`session_id`),
  CONSTRAINT `fk_msg_session` FOREIGN KEY (`session_id`)
    REFERENCES `chat_session` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='问答消息';

-- 6. 请求日志
CREATE TABLE IF NOT EXISTS `request_log` (
  `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `method`      VARCHAR(16)  NOT NULL COMMENT 'HTTP 方法',
  `path`        VARCHAR(255) NOT NULL COMMENT '请求路径',
  `status_code` INT          NOT NULL DEFAULT 200,
  `latency_ms`  DOUBLE       NOT NULL DEFAULT 0,
  `client_ip`   VARCHAR(64)  DEFAULT NULL,
  `created_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_log_path` (`path`),
  KEY `idx_log_created` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='请求日志';

-- 7. 评测集（验收要求 >= 100 条 FAQ）
CREATE TABLE IF NOT EXISTS `evaluation_set` (
  `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `question`         VARCHAR(512) NOT NULL,
  `reference_answer` TEXT         NOT NULL,
  `category`         VARCHAR(64)  NOT NULL DEFAULT 'faq',
  `created_at`       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_eval_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='校园 FAQ 评测集';

-- 8. 评测结果
CREATE TABLE IF NOT EXISTS `evaluation_result` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `eval_id`      BIGINT UNSIGNED NOT NULL COMMENT '评测条目',
  `question`     VARCHAR(512) NOT NULL,
  `recall`       DOUBLE       NOT NULL DEFAULT 0 COMMENT '检索召回',
  `faithfulness` DOUBLE       NOT NULL DEFAULT 0 COMMENT '答案忠实度',
  `latency_ms`   DOUBLE       NOT NULL DEFAULT 0,
  `created_at`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_result_eval` (`eval_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评测结果';

-- 种子数据（示例）
INSERT INTO `faq` (`category`, `question`, `answer`, `source`, `tags`) VALUES
('faq', '图书馆几点关门？', '图书馆周一至周日 8:00-22:00 开放，考试周延长至 23:00。', '图书馆官网《开放时间》', '图书馆,开放时间'),
('calendar', '本学期什么时候放寒假？', '根据校历，本学期寒假自 2026-01-15 开始。', '教务处《2025-2026 学年校历》', '校历,假期');
