-- insert dummy messages

DELIMITER $$
DROP PROCEDURE IF EXISTS insert_users $$
CREATE PROCEDURE insert_users()
 
BEGIN
	DECLARE userId INT;
	DECLARE email ,firstName ,lastName ,phone ,userName varchar(100);
	DECLARE done INT DEFAULT FALSE;
	

	DECLARE usersCursor CURSOR for 
	select u.User_ID,u.EMAIL,u.first_name,u.last_name,u.phone,u.user_name from `globex_prod`.`user` u;
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN usersCursor;
	
	read_loop: LOOP
		FETCH usersCursor into userId, email, firstName,lastName,phone,userName;
		IF done THEN
			LEAVE read_loop;
		END IF;

		select userId;	
		
		insert into `globex`.`t_user` values(
			userId,email,firstName,lastName,NULL,phone,userName,NULL,NULL,1,'2017-02-05 12:37:07','2017-02-05 12:37:07');
		
	END LOOP;
	CLOSE usersCursor;
END;






DROP PROCEDURE IF EXISTS insert_roles $$
CREATE PROCEDURE insert_roles()
 
BEGIN
	DECLARE userId INT;
	DECLARE email ,firstName ,lastName ,phone ,userName varchar(100);
	DECLARE done INT DEFAULT FALSE;
	

	DECLARE usersCursor CURSOR for 
	select u.User_ID,u.EMAIL,u.first_name,u.last_name,u.phone,u.user_name from `globex_prod`.`user` u;
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN usersCursor;
	
	read_loop: LOOP
		FETCH usersCursor into userId, email, firstName,lastName,phone,userName;
		IF done THEN
			LEAVE read_loop;
		END IF;

		select userId;	
		
		insert into `globex`.`t_user_role` values(NULL, '2012-09-27 20:28:41', 0, '2012-09-27 20:28:41', 0, 0, 'ROLE_SUPER_ADMIN', 1, '1', userId);
		
	END LOOP;
	CLOSE usersCursor;
END;

call insert_roles();