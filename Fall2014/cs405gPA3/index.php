
<!-- 
course: cs405g
Project: Assignment3
Date: 11/03/2014
Author: Yu Luo-->


<?php require "db_connection.php"?>
<html lang="en">
    <body>
      <div id="page">
        <h2>Yu Luo Assignment 3</h2>
        <p>Welcome to Assignment 3.</p>
       
       
       <?php
       $sqlCommands1 = "DROP TABLE IF EXISTS works, company, employee;";
       $result = mysqli_query($connection, $sqlCommands1);
    
       //Create company table
       $sqlCommand = "CREATE TABLE company (
		 		BankID int(10) NOT NULL,
  				company_name varchar(50) NOT NULL,
			 	city varchar(50) NOT NULL) ";
		if (mysqli_query($connection, $sqlCommand)){ 
   			 echo " </ hr> "; 
		} else { 
   		 echo "CRITICAL ERROR: company table has not been created.";
		}


         

 		//Create employee table
       $sqlCommand = "CREATE TABLE employee (
		 		person_name varchar(50) NOT NULL,
				street varchar(50) NOT NULL,
 			    city varchar(50) NOT NULL) ";
       if (mysqli_query($connection, $sqlCommand)){ 
   			 echo "</ hr>"; 
		} else { 
   			 echo "CRITICAL ERROR: employee table has not been created.";
   				}


         
//Create works table
    	$sqlCommand = "CREATE TABLE works (
		 		person_name varchar(50) NOT NULL,
  				BankID int(10) NOT NULL,
 			    salary int(100) unsigned NOT NULL) ";
		if (mysqli_query($connection, $sqlCommand)){ 
    		echo "</ hr>"; 
		} else { 
 			echo "CRITICAL ERROR: works table has not been created.";
		}
       
       
   
         //Populate company table
        $sqlCommand = "INSERT INTO company (BankID, company_name, city) VALUES
					(1001, 'First bank', 'lexington'),
					(1002, 'First bank', 'frankfort'),
					(2001, 'small bank corporation', 'richmond'),
					(3001, '3 5 bank', 'lexington'),
					(3002, '3 5 bank', 'richmond'),
					(3003, '3 5 bank', 'frankfort'),
					(4001, 'bank of america', 'lexington');";
		if (mysqli_query($connection, $sqlCommand)){ 
   			 echo "</ br>"; 
		} else { 
   			 echo "CRITICAL ERROR: Did not insert company";
		}
       
       
      
        
    //Populate employee table
    $sqlCommand = "INSERT INTO employee (person_name, street, city) VALUES
				('Mary', 'A', 'lexington'),
				('John', 'B', 'lexington'),
				('Grace', 'C', 'lexington'),
				('Joe', 'D', 'frankfort'),
				('Jeremy', 'E', 'lexington'),
				('Andy', 'D', 'richmond');";
	if (mysqli_query($connection, $sqlCommand)){ 
   		 echo "</ br>"; 
	} else { 
   		 echo "CRITICAL ERROR: Did not insert employee";
	}    


	//Populate
   $sqlCommand = "INSERT INTO works (person_name, BankID, salary) VALUES
				('Mary', 1001, 100000),
				('John', 1001, 50000),
				('Grace', 1002, 75000),
				('Joe', 1002, 125000),
				('Jeremy', 2001, 25000),
				('Jeremy', 1002, 50000),
				('John', 3001, 50000),
				('Andy', 4001, 20000);";
	if (mysqli_query($connection, $sqlCommand)){ 
   		 echo "</ br>"; 
   	} else { 
    	echo "CRITICAL ERROR: Did not insert employee";
	}    
        
       mysqli_free_result($sqlCommand);
       mysqli_free_result($result);
         
       
 ?>
       
       
       
    
	   <?php
		echo "<b>a) Find the names of all employees who work for First Bank Corporation.</ br></b>";
		
		$query="SELECT W.person_name 
				FROM works W, company C 
				WHERE W.BankID = C.BankID AND C.company_name = \"First bank\"";
		
		$result = mysqli_query($connection,$query);
		
		
		if(!$result){
			die("Database query failed");
		}
	
		while($row = mysqli_fetch_row($result)){
			echo "<hr />";
			print $row[0];
			echo "<hr />";
		}
		mysqli_free_result($result);		
		
	   ?>
	   
	   
	   
	   	<?php
		echo "<b>b) Find the names and cities of residence of all employees who work for First Bank Corporation.<br></b>";
		
		$query ="SELECT e.person_name, e.city 
				FROM employee e, works w, company c ";
		$query .="WHERE e.person_name = w.person_name 
				AND w.BankID = c.BankID AND c.company_name =\"First bank\"";
		
		$result = mysqli_query($connection, $query);
		
		    if(!$result){
                        die("Database query failed");
                }

                while($row = mysqli_fetch_row($result)){
                echo "<hr />";
                print "| ".$row[0]." | ".$row[1]."|";
                echo "<hr />";
                }
                mysqli_free_result($result);

	   ?>
	
	
	<?php
		echo "<b>c) Find the names, street address, and cities of residence of all employees who work<br> for First Bank Corporation and earn more than $10,000 per annum.<br></b>";
		
		$query ="SELECT e.person_name, e.street, e.city 
				FROM employee e, works w, company c ";
		$query .="WHERE e.person_name = w.person_name AND
	 				w.BankID = c.BankID AND
	 				c.company_name =\"First bank\" AND
					 w.salary > 10000";
		
		$result = mysqli_query($connection, $query);
		
		        if(!$result){
                        die("Database query failed");
                }

                while($row = mysqli_fetch_row($result)){
                
                  echo "<hr />";
                print "| ".$row[0]." | ".$row[1]." | ".$row[2]."|";
                echo "<hr />";
                }
                mysqli_free_result($result);
	   ?>

	   
	   	 <?php
		echo "<b>d) Find the names of all employees in this database who live in the same city as the company for which they work.<br></b>";
		
		$query ="SELECT DISTINCT e.person_name 
				FROM employee e, works w, company c ";
		$query .="WHERE e.person_name = w.person_name AND
	 			c.BankID = w.BankID AND
				 c.city = e.city";
		
		$result = mysqli_query($connection, $query);
		
		        if(!$result){
                        die("Database query failed");
                }

                while($row = mysqli_fetch_row($result)){
              	  echo "<hr />";
              	  print $row[0];
              	  echo "<hr />";
                }
                mysqli_free_result($result);
	   ?>
	
	   
	   
	   	   	 <?php
		echo "<b>e) Assume the companies may be located in several cities. Find all companies located in every city in which First bank is located.<br></b>";

		
		$query ="SELECT DISTINCT c1.company_name FROM company c1 ";
		$query .="WHERE c1.city IN 
				(SELECT c.city
 				FROM company c
				WHERE c.company_name =\"First bank\") AND
      			c1.company_name != \"First bank\"";
		
		$result = mysqli_query($connection, $query);

		         if(!$result){
                        die("Database query failed");
                }

                while($row = mysqli_fetch_row($result)){
                 	echo "<hr />";
               	 	print $row[0];
                	echo "<hr />";
                }
                mysqli_free_result($result);
	   ?>
	
	
	
		   	 <?php
		echo "<b>f) Find the employees who work for more than two companies. <br></b>";
		
		$query ="SELECT person_name, count(*) AS c
				FROM works
				GROUP BY person_name
				Having c>2";
	
		
		$result = mysqli_query($connection, $query);
		
		         if(!$result){
                        die("Database query failed");
                }

                while($row = mysqli_fetch_row($result)){
                  echo "<hr />";
                  print $row[0];
                  echo "<hr />";
                }
                mysqli_free_result($result);
	   ?>
	
	

<?php

echo "<b>g) Give all employees of First Bank Corporation a 10 percent salary raise <br></b>";

$query ="UPDATE works SET salary = salary * 1.1 ";
$query .="WHERE BankID IN (SELECT DISTINCT BankID FROM company WHERE company_name=\"First bank\")";


         $result = mysqli_query($connection, $query);

         if ($result){
                     
			$q = "SELECT * FROM works";

            $r = mysqli_query($connection, $q);
			while($row = mysqli_fetch_row($r)){
			echo "<hr />";
               		print "|".$row[0]." | ".$row[1]. " | ".$row[2]."|";
               		echo "<hr />";
			}
			
			  mysqli_free_result($r);
                } else {
                        echo "CRITICAL ERROR:Failed to give First Bank employees raise.";
                }
       

		 mysqli_free_result($result);
?>	



<?php

echo "<b>h). Give all employees in this database a 10 percent salary raise, unless the salary would be greater than $100,000. In such cases, give only a 3 percent raise.<br></b>";


$query ="UPDATE works SET salary = salary + salary * CASE WHEN Salary + salary * 0.1 >100000 then 0.03 else 0.1 end";


                $result = mysqli_query($connection, $query);


                       if ($result){
                    
                        $q = "SELECT * FROM works";

						$r = mysqli_query($connection, $q);
                        while($row = mysqli_fetch_row($r)){
                        echo "<hr />";
                        print "| ".$row[0]." | ".$row[1]. " | ".$row[2]."|";
                        echo "<hr />";}
                        
                        mysqli_free_result($r);
                        }else {
                        echo "CRITICAL ERROR:Failed to give First Bank employees raise.";}
            	
                mysqli_free_result($result); 
?>

<?php

echo "<b>i). Delete all tuples in the works relation for employees of Small Bank Corporation.<br>After Deletion: <br></b>";

$query ="DELETE FROM works WHERE BankID IN(
        SELECT Distinct BankID
        FROM company
        WHERE company_name = \"small bank corporation\")";


                $result = mysqli_query($connection, $query);

               if ($result){
              
                $q = "SELECT * FROM works";

				$r = mysqli_query($connection, $q);
                while($row = mysqli_fetch_row($r)){
               		 echo "<hr />";
                	 print $row[0]." ".$row[1]. " ".$row[2];
                	 echo "<hr />";
                }
                 mysqli_free_result($r);
        } else {
                echo "CRITICAL ERROR:Failed to give First Bank employees raise.";
        }

mysqli_free_result($result);

?>




	   
      </div>
	
	    <div id="footer">Copyright 2014 Yu Luo</div>

	</body>
</html>

<?php 
// Close database connection was set, then close it
	if(isset($connection)){
	mysqli_close($connection);
}
?>


