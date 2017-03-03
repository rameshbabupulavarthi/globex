DELIMITER $$
DROP PROCEDURE IF EXISTS get_schema $$
CREATE PROCEDURE get_schema()

BEGIN
	DECLARE tableData varchar(100);
	DECLARE done INT DEFAULT FALSE;

	DECLARE tableList CURSOR for
	SELECT TABLE_NAME FROM information_schema.`TABLES` T WHERE T.TABLE_SCHEMA='globex_prod';

	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	OPEN tableList;

	read_loop: LOOP
		FETCH tableList into tableData;
		IF done THEN
			LEAVE read_loop;
		END IF;

		select tableData;

		show create table `tableData`;

	END LOOP;
	CLOSE tableList;
END;

call get_schema();