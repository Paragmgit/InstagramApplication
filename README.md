# InstagramApplication
* This project is used to featch instagram data.
## Framwork and Language use in this Project
* Springboot 
* java
## Dependencies
* Spring Web
* MySql
* Spring boot DevTools
* jpa 
* lambok
## Flow of project
* Create four layers model , service , controller and dao.
* In model layer create user class and post class.
* In controller layer to performe featching all the request api.
* In service layer write a business logic of CRUD operation.
* In dao layer userRepository extends jpaRpository to connext dataBase.
## Constraints:
### user model will have-
* userId
* firstName
* lastName
* email
* phoneNumber
* age
### post model will have-
* postId
* createdDate
* updatedDate
* postData
* user conneted with post of manyTOone relation
## Endpoints to provided :
* create user
* getall user and userId
* updateuser
* create post 
* getall post by postId
* update post
