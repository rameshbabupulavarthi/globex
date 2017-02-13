-- insert dummy messages

DELIMITER $$
DROP PROCEDURE IF EXISTS insert_messages $$
CREATE PROCEDURE insert_messages()


BEGIN
	DECLARE message_count INT;
	DECLARE insert_count INT;

	select count(id) INTO message_count from t_message ;

	SELECT " Start printing ";

	select message_count;
	SET insert_count =0;

	BEGIN
	   WHILE insert_count  <= 30 DO
	   SELECT "Inserting ";
		SET insert_count = insert_count + 1;
		 insert into t_message values(insert_count,NULL,
		 'receiver_ids','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum','subject',NULL,'2012-09-27 20:28:41');			  
		END WHILE;	   	
	END;
	COMMIT;
END;

call insert_messages();