-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: courses_hub_db
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `is_correct` tinyint(1) NOT NULL,
  `created_date` date NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_question` (`question_id`),
  CONSTRAINT `fk_answer_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'Microsoft',0,'2024-03-20',1),(2,'Sun Microsystems',1,'2024-03-20',1),(3,'Google',0,'2024-03-20',1),(4,'Apple',0,'2024-03-20',1);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `is_active` tinyint(1) DEFAULT '1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Âm nhạc','Các khóa học về nhạc lý, nhạc cụ, thu âm, và sản xuất âm thanh',1,'2025-05-01 21:27:57'),(2,'Lập trình','Các khóa học lập trình web, mobile, backend, và công nghệ phần mềm',1,'2025-05-01 21:27:57'),(3,'Dữ liệu','Học phân tích dữ liệu, khoa học dữ liệu và trí tuệ nhân tạo',1,'2025-05-01 21:27:57'),(4,'Thiết kế','Thiết kế đồ họa, UI/UX, và các công cụ sáng tạo',1,'2025-05-01 21:27:57'),(5,'Kỹ năng mềm','Phát triển bản thân, giao tiếp, làm việc nhóm và tư duy phản biện',1,'2025-05-01 21:27:57'),(6,'Đầu tư','Tài chính cá nhân, chứng khoán, bất động sản, và crypto',1,'2025-05-01 21:27:57'),(7,'Marketing','Tiếp thị số, xây dựng thương hiệu, quảng cáo và truyền thông',1,'2025-05-01 21:27:57'),(8,'Tin học','Sử dụng máy tính, Word, Excel, PowerPoint và các phần mềm văn phòng',1,'2025-05-01 21:27:57'),(9,'Quay dựng','Quay phim, dựng video, hậu kỳ và kỹ thuật hình ảnh',1,'2025-05-01 21:27:57'),(10,'Nấu ăn','Học cách nấu các món ăn gia đình, dinh dưỡng và ngon miệng',1,'2025-05-01 21:35:46'),(11,'Yoga','Các bài tập yoga giúp cải thiện sức khỏe, thư giãn và giảm căng thẳng',1,'2025-05-01 21:35:46');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `certificate_url` varchar(255) NOT NULL,
  `issue_date` date NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_certificate_user` (`user_id`),
  KEY `fk_certificate_course` (`course_id`),
  CONSTRAINT `fk_certificate_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_certificate_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'certificate1.pdf','2024-03-20',1,1);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `chapter_order` int NOT NULL,
  `created_date` date NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chapter_course` (`course_id`),
  CONSTRAINT `fk_chapter_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (1,'Giới thiệu Java 1','<p>Tổng quan về Java</p>',1,'2025-05-10',1),(32,'gaggaga','<p>a</p>',2,'2025-05-13',1);
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1),(2);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  `user_id` int NOT NULL,
  `lecture_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_conversation_user` (`user_id`),
  KEY `fk_conversation_lecture` (`lecture_id`),
  CONSTRAINT `fk_conversation_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_conversation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `price` int NOT NULL,
  `average_star` double NOT NULL,
  `created_date` date NOT NULL,
  `image_url` text NOT NULL,
  `category_id` int DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  `course_status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  KEY `fk_teacher` (`teacher_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Lập trình Java','<p>Khóa học Java cơ bản đến nâng cao</p>',500000,4.5,'2024-03-20','https://tuyendung.kfcvietnam.com.vn/Data/Sites/1/media/blog/lap-trinh-java.jpg',2,2,0),(2,'Lập trình Web','Khóa học HTML, CSS, JavaScript',300000,4.2,'2024-03-20','https://vtiacademy.edu.vn/upload/images/lap-trinh-web.jpg',2,2,0),(3,'Marketing Online Tổng Quan','Cung cấp cái nhìn toàn diện về các kênh marketing online hiệu quả nhất hiện nay.',799000,4.5,'2023-02-20','https://placehold.co/600x400/blue/white?text=Marketing+Online',7,2,0),(4,'Tiếng Anh Giao Tiếp Cho Người Đi Làm','Cải thiện kỹ năng nghe, nói, đọc, viết tiếng Anh trong môi trường công sở.',650000,4.8,'2022-11-10','https://placehold.co/600x400/green/white?text=Tiếng+Anh+Công+Sở',5,2,0),(5,'Thiết Kế Đồ Họa với Photoshop','Học cách sử dụng thành thạo Adobe Photoshop để thiết kế banner, poster, chỉnh sửa ảnh.',899000,4.6,'2023-03-05','https://placehold.co/600x400/red/white?text=Photoshop+Design',4,2,0),(6,'Quản Lý Dự Án Chuyên Nghiệp','Trang bị kiến thức và kỹ năng quản lý dự án theo chuẩn quốc tế PMI.',1200000,4.9,'2023-04-12','https://placehold.co/600x400/purple/white?text=Project+Management',5,2,0),(7,'Kỹ Năng Mềm Thiết Yếu','Phát triển các kỹ năng mềm quan trọng như giao tiếp, làm việc nhóm, giải quyết vấn đề.',399000,4.4,'2022-09-25','https://placehold.co/600x400/teal/white?text=Kỹ+Năng+Mềm',5,2,0),(8,'Đầu Tư Chứng Khoán Cho Người Mới','Hướng dẫn các bước cơ bản để tham gia thị trường chứng khoán một cách an toàn và hiệu quả.',550000,4.7,'2023-05-18','https://placehold.co/600x400/brown/white?text=Chứng+Khoán+F0',6,2,0),(9,'Nấu Ăn Gia Đình Cơ Bản','Học cách chế biến các món ăn ngon, đơn giản, dinh dưỡng cho bữa cơm gia đình.',350000,4.6,'2023-06-01','https://placehold.co/600x400/pink/white?text=Nấu+Ăn+Gia+Đình',10,2,0),(10,'Yoga Tại Nhà Cho Người Bận Rộn','Các bài tập yoga đơn giản, có thể thực hiện tại nhà để cải thiện sức khỏe và giảm căng thẳng.',450000,4.8,'2023-07-11','https://placehold.co/600x400/cyan/white?text=Yoga+Tại+Nhà',11,2,0),(11,'Lập Trình Python Từ A Đến Z','Khóa học đầy đủ về Python, từ cú pháp cơ bản đến ứng dụng thực tế.',999000,4.9,'2023-08-22','https://placehold.co/600x400/lime/white?text=Python+A-Z',2,2,0),(12,'Content Marketing Hiệu Quả','Xây dựng chiến lược và sản xuất nội dung thu hút, giữ chân khách hàng.',750000,4.5,'2022-12-15','https://placehold.co/600x400/indigo/white?text=Content+Marketing',7,2,0),(13,'Học Guitar Đệm Hát Cơ Bản','Tự tin đệm hát những bài hát yêu thích sau khóa học guitar này.',500000,4.7,'2023-09-03','https://placehold.co/600x400/amber/white?text=Guitar+Đệm+Hát',1,2,0),(14,'Excel Nâng Cao Cho Dân Văn Phòng','Làm chủ các hàm, công cụ phân tích dữ liệu và tự động hóa báo cáo trong Excel.',600000,4.6,'2023-10-10','https://placehold.co/600x400/deeporange/white?text=Excel+Nâng+Cao',8,2,0),(15,'Thiết Kế Web Cơ Bản với HTML & CSS','Tự tay xây dựng giao diện trang web tĩnh đầu tiên của bạn.',550000,4.8,'2023-11-01','https://placehold.co/600x400/bluegrey/white?text=HTML+CSS',4,2,0),(16,'Kỹ Năng Bán Hàng Đỉnh Cao','Nắm vững quy trình và bí quyết chốt sale hiệu quả, tăng doanh số đột phá.',850000,4.7,'2024-01-05','https://placehold.co/600x400/lightgreen/white?text=Kỹ+Năng+Sales',5,2,0),(17,'Lập Trình Android Cơ Bản','Xây dựng ứng dụng Android đầu tiên với ngôn ngữ Kotlin hoặc Java.',950000,4.6,'2024-02-14','https://placehold.co/600x400/darkred/white?text=Android+Dev',2,2,0),(18,'Nghệ Thuật Nhiếp Ảnh Cho Smartphone','Biến chiếc điện thoại thành công cụ sáng tạo ảnh chuyên nghiệp.',400000,4.5,'2023-12-20','https://placehold.co/600x400/gold/black?text=Mobile+Photography',9,2,0),(19,'Tiếng Nhật Sơ Cấp N5','Làm quen với bảng chữ cái Hiragana, Katakana và cấu trúc ngữ pháp cơ bản.',700000,4.8,'2024-03-10','https://placehold.co/600x400/darkblue/white?text=Tiếng+Nhật+N5',3,2,0),(20,'Digital Marketing Foundation','Nền tảng vững chắc về tiếp thị kỹ thuật số, bao gồm SEO, SEM, Social Media.',800000,4.7,'2024-04-15','https://placehold.co/600x400/darkcyan/white?text=Digital+Marketing',7,2,0),(21,'Quản Trị Nhân Sự Hiện Đại','<p>Các phương pháp quản lý và phát triển nguồn nhân lực hiệu quả trong doanh nghiệp.</p>',1100000,4.9,'2024-05-20','https://placehold.co/600x400/darkmagenta/white?text=Quản+Trị+Nhân+Sự',5,NULL,1),(22,'Nhật Nguyễn','<h1><strong>Giới thiệu khóa học:</strong></h1><p>Test</p>',31410,0,'2025-05-05','https://res.cloudinary.com/dslbgvdao/image/upload/v1746460337/qfciomodvi9e5iwl1jss.jpg',1,18,0),(39,'Nguyễn Đình Nhật','<p><br></p>',10,0,'2025-05-17','http://res.cloudinary.com/dslbgvdao/image/upload/v1747476808/gq3d2xtujqtisemwfp29.png',1,NULL,1),(40,'test','<p><br></p>',0,0,'2025-05-17','http://res.cloudinary.com/dslbgvdao/image/upload/v1747492717/dv3ycbho6yglhuuv7aqr.png',1,2,2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_progress`
--

DROP TABLE IF EXISTS `course_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_progress` (
  `id` int NOT NULL AUTO_INCREMENT,
  `progress` double NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `chapter_id` int NOT NULL,
  `lecture_id` int NOT NULL,
  `is_complete_course` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_courseprogress_user` (`user_id`),
  KEY `fk_courseprogress_course` (`course_id`),
  KEY `fk_courseprogress_chapter` (`chapter_id`),
  KEY `fk_courseprogress_lecture` (`lecture_id`),
  CONSTRAINT `fk_courseprogress_chapter` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_courseprogress_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_courseprogress_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_courseprogress_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_progress`
--

LOCK TABLES `course_progress` WRITE;
/*!40000 ALTER TABLE `course_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_user_enroll`
--

DROP TABLE IF EXISTS `course_user_enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_user_enroll` (
  `id` int NOT NULL AUTO_INCREMENT,
  `enroll_date` date NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `payment_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_courseuserenroll_user` (`user_id`),
  KEY `fk_courseuserenroll_course` (`course_id`),
  KEY `fk_courseuserenroll_payment` (`payment_id`),
  CONSTRAINT `fk_courseuserenroll_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_courseuserenroll_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_courseuserenroll_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_user_enroll`
--

LOCK TABLES `course_user_enroll` WRITE;
/*!40000 ALTER TABLE `course_user_enroll` DISABLE KEYS */;
INSERT INTO `course_user_enroll` VALUES (1,'2024-03-20',1,1,1);
/*!40000 ALTER TABLE `course_user_enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `document_url` varchar(255) DEFAULT NULL,
  `lecture_order` int NOT NULL,
  `created_date` date NOT NULL,
  `chapter_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lecture_chapter` (`chapter_id`),
  CONSTRAINT `fk_lecture_chapter` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (11,'rer','http://res.cloudinary.com/dslbgvdao/video/upload/v1747329466/hioagkllynkgha2svd0p.mp4','http://res.cloudinary.com/dslbgvdao/image/upload/v1747329471/gsif1elhwsyvgel4v17t.pdf',1,'2025-05-13',32),(15,'lecture 2 test','http://res.cloudinary.com/dslbgvdao/video/upload/v1747303883/dv2b5kqq2tldpbekvrgg.mp4','http://res.cloudinary.com/dslbgvdao/image/upload/v1747329098/uscbr94zcgiae58rauty.pdf',1,'2025-05-15',1);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `chat_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_message_chat` (`chat_id`),
  KEY `fk_message_user` (`user_id`),
  CONSTRAINT `fk_message_chat` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'Hello, how can I help you?',1,2),(2,'I have a question about Java variables.',1,1),(3,'Sure! What do you need help with?',1,2),(4,'How do I declare an integer variable in Java?',1,1),(5,'You can declare it like this: int number = 10;',1,2),(6,'Hi, can you explain CSS flexbox?',2,1),(7,'Of course! Flexbox is a layout model in CSS that helps align items efficiently.',2,2),(8,'How do I center a div using flexbox?',2,1),(9,'You can use: display: flex; justify-content: center; align-items: center;',2,2);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `method` varchar(50) DEFAULT NULL,
  `created_date` date NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'UNPAID',
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payment_user` (`user_id`),
  CONSTRAINT `fk_payment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,500000,'Credit Card','2024-03-20','PAID',1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `type` int NOT NULL,
  `created_date` date NOT NULL,
  `test_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_test` (`test_id`),
  CONSTRAINT `fk_question_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Ngôn ngữ lập trình Java được phát triển bởi công ty nào?',1,'2024-03-20',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` text NOT NULL,
  `type` enum('pdf','video','link') NOT NULL,
  `chapter_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chapter_id` (`chapter_id`),
  CONSTRAINT `resource_ibfk_1` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `created_date` date NOT NULL,
  `is_teacher_response` tinyint(1) NOT NULL,
  `conversation_id` int NOT NULL,
  `parent_response_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_response_conversation` (`conversation_id`),
  KEY `fk_response_response` (`parent_response_id`),
  CONSTRAINT `fk_response_conversation` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_response_response` FOREIGN KEY (`parent_response_id`) REFERENCES `response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_comment`
--

DROP TABLE IF EXISTS `review_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `created_date` date NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reviewcomment_user` (`user_id`),
  KEY `fk_reviewcomment_course` (`course_id`),
  CONSTRAINT `fk_reviewcomment_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reviewcomment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_comment`
--

LOCK TABLES `review_comment` WRITE;
/*!40000 ALTER TABLE `review_comment` DISABLE KEYS */;
INSERT INTO `review_comment` VALUES (1,'Khóa học rất bổ ích!','2024-03-20',1,1);
/*!40000 ALTER TABLE `review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_star`
--

DROP TABLE IF EXISTS `review_star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_star` (
  `id` int NOT NULL AUTO_INCREMENT,
  `star` int NOT NULL,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reviewstar_user` (`user_id`),
  KEY `fk_reviewstar_course` (`course_id`),
  CONSTRAINT `fk_reviewstar_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reviewstar_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_star`
--

LOCK TABLES `review_star` WRITE;
/*!40000 ALTER TABLE `review_star` DISABLE KEYS */;
INSERT INTO `review_star` VALUES (1,5,1,1),(2,4,1,2);
/*!40000 ALTER TABLE `review_star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `user_id` int NOT NULL,
  `avatar_url` varchar(255) NOT NULL,
  `average_rating` float DEFAULT '0',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'http://res.cloudinary.com/dslbgvdao/image/upload/v1747414260/wvhjs1q4rc1xjbkhajuw.jpg',0),(4,'https://via.placeholder.com/150?text=GV+4',0),(5,'https://via.placeholder.com/150?text=GV+5',0),(6,'https://via.placeholder.com/150?text=GV+6',0),(7,'https://via.placeholder.com/150?text=GV+7',0),(8,'https://via.placeholder.com/150?text=GV+8',0),(9,'https://via.placeholder.com/150?text=GV+9',0),(10,'https://via.placeholder.com/150?text=GV+10',0),(11,'https://via.placeholder.com/150?text=GV+11',0),(12,'https://via.placeholder.com/150?text=GV+12',0),(13,'https://via.placeholder.com/150?text=GV+13',0),(14,'https://via.placeholder.com/150?text=GV+14',0),(16,'https://via.placeholder.com/150?text=GV+16',0),(17,'https://via.placeholder.com/150?text=GV+17',0),(18,'https://via.placeholder.com/150?text=GV+18',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `duration` int NOT NULL,
  `chapter_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_test_chapter` (`chapter_id`),
  CONSTRAINT `fk_test_chapter` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'Kiểm tra Java cơ bản',30,1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_submission`
--

DROP TABLE IF EXISTS `test_submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_submission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` double NOT NULL,
  `submit_date` date NOT NULL,
  `user_id` int NOT NULL,
  `test_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_testsubmission_user` (`user_id`),
  KEY `fk_testsubmission_test` (`test_id`),
  CONSTRAINT `fk_testsubmission_test` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_testsubmission_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_submission`
--

LOCK TABLES `test_submission` WRITE;
/*!40000 ALTER TABLE `test_submission` DISABLE KEYS */;
INSERT INTO `test_submission` VALUES (1,8.5,'2024-03-20',1,1),(3,9,'2024-03-20',2,1);
/*!40000 ALTER TABLE `test_submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn Văn A','a@gmail.com','ROLE_STUDENT','nguyenvana','123456','0123456789',1,'Hà Nội','2024-03-20'),(2,'Trần Thị B','b@gmail.com','ROLE_TEACHER','tranthib','$2a$10$FAea5LVltg0oE7botuEWXOYIgh.gAcSJNZGkqX3y2hBwW5LkKLbwa','0987654321',0,'TP.HCM','2024-03-20'),(3,'D','a@gmail.com','ROLE_ADMIN','dhthanh','$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','3124312432',1,'TP. HCM','2024-04-14'),(4,'Nguyễn Văn A','a1@example.com','ROLE_TEACHER','nguyenvana','123456','0123456789',1,'Hà Nội','2025-05-17'),(5,'Trần Thị B','b2@example.com','ROLE_TEACHER','tranthib','123456','0123456790',0,'TP. Hồ Chí Minh','2025-05-17'),(6,'Lê Văn C','c3@example.com','ROLE_TEACHER','levanc','123456','0123456791',1,'Đà Nẵng','2025-05-17'),(7,'Phạm Thị D','d4@example.com','ROLE_TEACHER','phamthid','123456','0123456792',0,'Cần Thơ','2025-05-17'),(8,'Đỗ Văn E','e5@example.com','ROLE_TEACHER','dovane','123456','0123456793',1,'Hải Phòng','2025-05-17'),(9,'Vũ Thị F','f6@example.com','ROLE_TEACHER','vuthif','123456','0123456794',0,'Huế','2025-05-17'),(10,'Ngô Văn G','g7@example.com','ROLE_TEACHER','ngovang','123456','0123456795',1,'Nha Trang','2025-05-17'),(11,'Hoàng Thị H','h8@example.com','ROLE_TEACHER','hoangthih','123456','0123456796',0,'Bình Dương','2025-05-17'),(12,'Bùi Văn I','i9@example.com','ROLE_TEACHER','buivani','123456','0123456797',1,'Đồng Nai','2025-05-17'),(13,'Đặng Thị J','j10@example.com','ROLE_TEACHER','dangthij','123456','0123456798',0,'Quảng Ninh','2025-05-17'),(14,'Phan Văn K','k11@example.com','ROLE_TEACHER','phanvank','123456','0123456799',1,'Thái Nguyên','2025-05-17'),(16,'Tạ Văn M','m13@example.com','ROLE_TEACHER','tavanm','123456','0123456801',1,'Hà Tĩnh','2025-05-17'),(17,'Dương Thị N','n14@example.com','ROLE_TEACHER','duongthin','123456','0123456802',0,'Vĩnh Long','2025-05-17'),(18,'Trịnh Văn O','o15@example.com','ROLE_TEACHER','trinhvano','123456','0123456803',1,'Lạng Sơn','2025-05-17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-17 22:53:52
