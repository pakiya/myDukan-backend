# myDukan-backend
I have done my dukan done basic api and implement elastic search.
First i have setup project with multiple environment like dev, staging and prod.
I have added spring security filter also. every api call need X-ADMIN-SECRET header it is compulsory
I am using MVC architecture like every api point is able in controller class, business logic is in the server and service impl class, every mysql query is connect with dao and every dao connect with presenter manager class.
For google sheet I am using Google API to fetch google sheet data in our spring application. (you need to change api key json file for run this project )
Elasticsearch is implemented i am saving product data in elasticsearch with intex
Data is saving in Elasticsearch on two cases first if user will create product from create product api so after creating I am saving data in elasticsearch and 2nd is whenever you hit google sheet api so after the response i am storing in ES
I have done POST=> Create product api
update product api which is POST=> update price or group
POST=>Group list with product and every listing call is a pagination call.
POST=> product search api with pagination.

there is two MySql table 
=> Product
=> Group

CREATE TABLE `Groups` (
  `name` varchar(45) NOT NULL DEFAULT '',
  `description` varchar(100) DEFAULT '',
  `isActive` tinyint(4) DEFAULT '1',
  `stamp_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `stamp_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


CREATE TABLE `Product` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `module` varchar(100) DEFAULT '',
  `serial_no` varchar(36) DEFAULT '',
  `group_id` varchar(36) NOT NULL DEFAULT '',
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

