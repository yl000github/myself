<?php 
class DB { 
    private static $dbMode=0;//0:local,1:sina,2:bae,3:126
    private static $objInstance; 
    
    /* 
     * Class Constructor - Create a new database connection if one doesn't exist 
     * Set to private so no-one can create a new instance via ' = new DB();' 
     */ 
    private function __construct() {} 
    
    /* 
     * Like the constructor, we make __clone private so nobody can clone the instance 
     */ 
    private function __clone() {} 
    
    /* 
     * Returns DB instance or create initial connection 
     * @param 
     * @return $objInstance; 
     */ 
    public static function getInstance(  ) { 
            
        if(!self::$objInstance){
        	switch ( self::$dbMode ) {
				case 0:
					$dsn='mysql:host=127.0.0.1;dbname=myself;';
        			$user='root';
        			$password='yl123';
				break;
				case 1:
					$dsn="mysql:host=".SAE_MYSQL_HOST_M.";port=".SAE_MYSQL_PORT.";dbname=".SAE_MYSQL_DB;
        			$user=SAE_MYSQL_USER;
        			$password=SAE_MYSQL_PASS;
        			break;
        		case 2:
					$dsn="mysql:host=sqld.duapp.com;port=4050;dbname=ryPggfzZyqBZbOvAxRnC";
        			$user="";
        			$password="";
        		case 3:
					$dsn='mysql:host=127.0.0.1;port=4444;dbname=annualmeeting;';
        			$user='admin';
        			$password='ALLqq12';
				break;
				
			} 
            
            self::$objInstance = new PDO($dsn, $user, $password);
            self::$objInstance->exec('SET NAMES "utf8"');
            self::$objInstance->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); 
        } 
        
        return self::$objInstance; 
    
    } # end method 
    
    /* 
     * __callStatic called dynamicaly
     * Passes on any static calls to this class onto the singleton PDO instance 
     * @param $chrMethod, $arrArguments 
     * @return $mix 
     */ 
    final public static function __callStatic( $chrMethod, $arrArguments ) { 
            
        $objInstance = self::getInstance(); 
        
        return call_user_func_array(array($objInstance, $chrMethod), $arrArguments); 
        
    } # end method 
    
} 

?>