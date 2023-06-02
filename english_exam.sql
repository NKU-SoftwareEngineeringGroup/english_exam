/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 39.105.177.215:3306
 Source Schema         : english_exam

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 02/06/2023 16:45:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '25d55ad283aa400af464c76d713c07ad');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `paper_id` int NULL DEFAULT NULL,
  `student_id` int NULL DEFAULT NULL,
  `choice_answer` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `subjective1_answer` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `subjective2_answer` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `choice_score` int NULL DEFAULT NULL,
  `subjective1_score` int NULL DEFAULT NULL,
  `subjective2_score` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `paper_id`(`paper_id`) USING BTREE,
  INDEX `stu_id`(`student_id`) USING BTREE,
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `exam_paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES (1, 1, 1, 'A;A;A;A;A;', 'aaaa', 'bbbb', 10, NULL, NULL);
INSERT INTO `answer` VALUES (3, 5, 1, 'A;A;A;A;A;', 'aaaa', 'bbbb', 0, NULL, NULL);
INSERT INTO `answer` VALUES (4, 6, 1, 'A;A;A;A;A;', 'aaaa', 'bbbb', 0, NULL, NULL);
INSERT INTO `answer` VALUES (5, 11, 7, '1;2;3;4;', '111', '222', 0, 100, 100);
INSERT INTO `answer` VALUES (6, 11, 7, 'A;A;A;A;', '1', '1', 8, NULL, NULL);
INSERT INTO `answer` VALUES (7, 12, 7, 'A;A;A;A;', '11', '11', 8, 10, 5);

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES (1, 'test1', 'path1', 'A;A;A;A;A;', '2023-05-19 15:32:54', '2023-05-23 15:20:58');
INSERT INTO `exam_paper` VALUES (2, '测试试卷2', 'af8d36c6-1286-4c6d-890b-d8dcbfc867b3.pdf', 'B;B;B;B;B;', '2023-05-20 20:16:17', '2023-05-20 20:16:20');
INSERT INTO `exam_paper` VALUES (3, '测试试卷2', '9f4d3ed3-d6cb-40d3-be66-02544d83585a.pdf', 'B;B;B;B;B;', '2023-05-20 20:16:17', '2023-05-20 20:16:20');
INSERT INTO `exam_paper` VALUES (4, '测试试卷2', '1dd22ff7-0482-4ab8-85b6-15fba69f8517.pdf', 'B;B;B;B;B;', '1970-01-20 19:56:25', '1970-01-20 20:09:23');
INSERT INTO `exam_paper` VALUES (5, '测试试卷2', '498f7843-623e-49e7-b5c6-7a31936b484e.pdf', 'B;B;B;B;B;', '2023-05-20 20:16:17', '2023-05-29 20:16:16');
INSERT INTO `exam_paper` VALUES (6, '测试试卷2', 'fefcb758-0b88-4d6f-b8a9-5ee31436f513.pdf', 'B;B;B;B;B;', '2023-05-20 20:16:17', '2023-05-29 20:16:16');
INSERT INTO `exam_paper` VALUES (8, '试卷1', 'aa0ccfa1-9606-4ef5-b821-a52eda050427.pdf', 'A;B;C;D;', '2023-05-20 20:16:17', '2023-05-29 20:16:16');
INSERT INTO `exam_paper` VALUES (9, '新的试卷', '3b8e1900-803f-4c86-804a-ea0ab67a8020.pdf', 'A;B;C;D;', '2023-05-20 20:16:17', '2023-05-29 20:16:16');
INSERT INTO `exam_paper` VALUES (10, '试卷33', '3479f5aa-f667-4780-9c13-68ea696b4cbd.pdf', 'A;A;D;D;', '2023-05-01 00:00:00', '2023-05-31 19:50:55');
INSERT INTO `exam_paper` VALUES (11, '1', 'd5023d79-f8bd-4e06-971a-85fa52e4f76e.pdf', 'A;A;A;A;', '2023-05-31 00:00:00', '2023-06-30 00:00:00');
INSERT INTO `exam_paper` VALUES (12, '2', '805b696a-67de-4e6d-96cb-f604f8d27b36.pdf', 'A;A;A;A;', '2023-06-01 22:56:23', '2023-06-30 00:00:00');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enroll` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'student1', '111111222222223333', '25d55ad283aa400af464c76d713c07ad', 0);
INSERT INTO `student` VALUES (2, 'student2', '111111222222223334', '25d55ad283aa400af464c76d713c07ad', 0);
INSERT INTO `student` VALUES (3, 'student2', '111111222222223335', '25d55ad283aa400af464c76d713c07ad', 0);
INSERT INTO `student` VALUES (4, 'student4', '111111222222223336', '25d55ad283aa400af464c76d713c07ad', 0);
INSERT INTO `student` VALUES (5, 'student_login_test', '111111222222223337', '25d55ad283aa400af464c76d713c07ad', 1);
INSERT INTO `student` VALUES (6, '123', '1231', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `student` VALUES (7, 's1', '1', 'c4ca4238a0b923820dcc509a6f75849b', 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 't1', '25d55ad283aa400af464c76d713c07ad');

-- ----------------------------
-- Table structure for valid_paper
-- ----------------------------
DROP TABLE IF EXISTS `valid_paper`;
CREATE TABLE `valid_paper`  (
  `id` int NULL DEFAULT NULL,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `valid_paper_ibfk_1` FOREIGN KEY (`id`) REFERENCES `exam_paper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of valid_paper
-- ----------------------------
INSERT INTO `valid_paper` VALUES (12);

SET FOREIGN_KEY_CHECKS = 1;
