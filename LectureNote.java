s1 = "abc"
s2 = "abc"


el = [a,b,c,d,e,f,g] -> abcdefg

StringBuilder sb = new StringBuilder();
for(int i=0; i<el.length; i++){
	sb.append(el[i]);
}
return sb.toString();

i=0; sb = "ab"

s = s + el[i]


s = "http://www.xyz.com"

s.split(".") --> [http://www,  xyz,  com]
StringTokenizer



//final

public final class Employee{
	
	private final StringBuilder name = new StringBuilder("Jack");
	private final List<String> titles = new ArrayList<>();
	private static Addres address;

	public Employee(String name){
		this.name = name;
		this.name.append("Ma");
	}

	public Employee(String name, Address address){
		this.name = name;
		this.address = address;
		titles.add("CEO");
		titles.add("Manager");
	}

	public final  String getName(){
		return name;
	}

	public void performWork(String work){
		System.out.println(name +" performs " + work);
	}
}

public class Manager extends Employee{

	
	public  String getName(){
		return name + "a";
	}
}

m.getName()


try{
	connect to database
	write date
}catch(exception){
	do something
}finally{
	close database connection
}


How to make class immutable
1) make class final 
2) make fields private -- only accessible inside
3) do not give setter method.


public class Utils, helper{

	Address address;

	public static List<String> splitStringByComma(String input){
		return Arrays.asList(input.split(","));
	}

	public void setAddress(Address a){
		this.address = a;
	}
}


public class c1{
	Utils.splitStringByComma("mystring,mystring");
}

public class c2{
	Utils.splitStringByComma("mystring,mystring");
}

public class c3{
	Utils.splitStringByComma("mystring,mystring");
}


Object --> class -  --- -> object/instance
			     ---> abstract class
		interface --> class
enum --> 



---
public class EnglishBook implements Readable{

	String name;
}


public interface Readable{

}


public interface Atm {
	deposit(Money);
	withdraw(int amount);
}


public class AtmImpl implements Atm{
	deposit(Money m){
		logic;
	}
}

1. class has all concrete method --has implementation
2. interface has all abstract method -- no implementation
3. abstract class has Both

public abstract class Cat {
	public void meow();

	public int getLife(){
		return 9;
	}
}

public class BossCat extends Cat{
	public void meow(){
		print("Boss");
	}
}



public class Singleton{

	private static Singleton instance;

	private Singleton(){}

	public static synchronized Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}

	//prevent Object.clone()
	@Override
	protected Singleton clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}

	//prevent serializble
	protected Singleton readResolve(){
		return getInstance();
	}

}


Singleton s = Singleton.getInstance();

camel case


public DBConnection getDBConnection(String db){
	if("oracle".equals(db)){
		OracleConnection oc = new OracleConnection()
		return oc.getConnection();
	}
}

public abstract class Phone{
	public abstract Phone makePhones();

	public Message sendMsg(String input){
		Message m = new Message();
		m.setStatus(200);
		m.setSentTime(new Date());
		m.setBody(input);
		return m;
	}
}

public class Iphone extends Phone{
	public Iphone makePhones(){
		//add Apple Chip
		//add Camera
		return iphone;
	}
}

public class Samsung extends Phone{
	public Samsung makePhones(){
		//add Intel Chip
		//add Camera
		return iphone;
	}
}

public class ToyPhone extends Phone{
	public ToyPhone makePhones(){
		return null;
	}

	@Override
	public Message sendMsg(String input){
		return new Message("not valid");
	}
}


public class PhoneFactory{

	public static Phone getPhone(String brand){
		if("iphone".equals(brand)){
			Phone iphone = new IPhone();
			return iphone.makePhones();
		}
		if("samsung".equals(brand)){
			Phone ss = new Samsung();
			return ss.makePhones();
		}
	}
}


PhoneFactory.getPhone("iphone");

heap memory Xmx Xms

memory leak  vs out of memory error


throw throws

 methodA(){
 	try{
 	methodB();
 	}catch(){

 	}
 }


 methodB() throws Exception{
 	try{
 		...
 	}catch(){
 		if(ex == SQLEXP){
 			throw ex;
 		}else{
 			throw new Exception("Something happened");
 		}
 	}
 }


 public interface MyInteface{

 	public  static final String companyName = "X";
 	public void readMe();
 }


MyInteface.companyName;

 public class C implements MyInterface{
 	static final String id = "a";
 	override readMe(){
 		id = "b";
 	}
 }


C c1 = new C();
c1.id;
C c2 = new C();
c2.id;


C.id




public class C implements Interface1{

	Employee e;
}



public class Chelper{

	int X = 10;

	public void printEmployee(){
		int a = 3
	}

	public void m2{
		a = 4;
	}
}

Chelper ch1 = new Chelerp();
ch1.X = 12;
Chelper ch2 = new Chelper();
ch2.X = 15;


variable:
	1. instance varilabe
	2. class/static variable
	3. local variable


List<Employee> employees = new ArrayList<Employee>();

ArrayList<Employee> employees1 = new ArrayList<Employee>();
List<Employee> employees = (List<Employee>)employees1

public ArrayList<Employee> getList(){
	return new ArrayList<Employee>();
}

m2(ArrayList<Employee> list){

}


Map<Integer, String> map = new LinkedHashMap<>();
map.put(1, "One");
map.put(2, "Two");
map.put(3, "Three");
for(Map.Entry<Integer, String> entry : map.entrySet()){
	System.out.println(entry.getValue() + "." + entry.getValue());
}
for(Integer k : map.keySet()){
	System.out.println(map.get(k) + "." + map.get(k));
}

Map<Employee, Double> map = new HashMap<>();
Employee e1 = new Employee("Jack");
Employee e2 = new Employee("Pony");
Employee e3 = new Employee("Jack");
//e1;

//new Employee("Elon");
map.put(e1, 1.0);
map.put(e2, 2.0);
map.put(e3, 3.0);

map.get(e1); --> 1.0

map.get(e1) --> 3.0
map.get(e3) --> 3.0

map.get(e1) --> 3.0
map.get(e3) --> 3.0


s1.equals(s2);

e1 = {"sales","Jack",123}
e3 = {"accounting","Jack",345}

{"accounting","Jack",345} --> 3.0;

map.keySet();


public class Employee{

	Department department;
	String name;
	int id;

	@override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		if(! (o instanceof Employee){
			return false;
		}
		Employee oe = (Employee)o;

		return this.name.equals(oe.name);
	}

	@Override
	public int hashCode(){
		return Object.hash(this.name);

	}

}

public class Person extends Employee{}


Employee e = new Employee("sales", "jack");
Object o = new Person("Jack");
e.equals(o);


for(int i=0; i<list.size(); i++){
	if(list.get(i).getName().equals("Jack")){
		list.remove(list.get(i));
	}
}

map.entrySet()/keySet()/values()


Iterator<Employee> it = map.keySet().iterator();
while(it.hasNext()){
	Employee e = it.next();
	if(e.getName().equals("Jack")){
		map.remove(e);
	}
}


Iterator<Employee> it = list.iterator();
while(it.hasNext()){
	Employee e = it.next();
	if(e.getName().equals("Jack")){
		it.remove();
	}
}




ConcurrentModificationException


public static Singleton getInstance(){
	synchronized(instance/this){
		if(instance != null){
			instance = new Singleton();
		}
		return instance;
	}
}


Sync:
	
	m1
	m2
	m3

Async


portal -- URL

GET(URL) --> DATA


public void callBackend(){

	httpClient.get(URL)
		  		.then(data -> processData());
		  		---> console.log("done");
	eval(1+2);
	eval(2+3);
}

Sync: Done, 3, 5


syncrhonized <> unsyncrhonized



sync <> async



public interface Comparable{

	public int compare(int a, int b);
}

//1. a > b, -> b,a
//2. a > b --> a, b
//3. a^2 > b^2 -- a, b

public Impl implements Comparable{

	@Override
	public int compare(int a, int b){
		return b - a;
	}
}

Impl impl = new Impl(int a, int b);

Collections.sort(list, impl);


///
Collections.sort(list, new Impl(int a, int b){
	@Override
	public int compare(int a, int b){
		return b - a;
	}
});


(input) -> {logic}

(int a, int b) -> {
	return b - a;
}


Collections.sort(list, (int a, int b) -> { return b - a; });

(a, b) -> {
	return b - a;
}

(a, b) -> b - a

(a, b) -> System.out.print(a + b)

(a) -> a*a

a -> a * a


List<Employee> employees

for(...)


List<Double> salaries = employees.stream().map(e -> e.getSalary() * 1.2).collect(Collectors.toList());

List<String> names = employees.stream().map(e -> e.getName()).collect(Collectors.toList());


List<Employee> salaries = employees.stream().filter(e -> e.getSalary() > 100).collect(Collectors.toList());


employees.forEach(e -> e.setSalary(e.getSalary * 1.2));



{"new jersey", "new york", "texas", "dc"}

Map<String, List<String>> categories = list.stream
								.collect(Collectors
										.groupingBy(state -> state.startWith("new") ? "new" : "notnew", 
															 );

"new" -> [new jersey, new york]
"notnew" -> [texas, dc]


Map<String, List<String>> categories = list.stream
								.collect(Collectors
										.groupingBy(state -> state, Collectors.toList());

new jersey  -> [new jersey]


map1 -> "Jan" : ["Jan", "Jan"]
		"Feb" : ["Feb"]
		"July": ["July"]

map1 -> "Jan" : [1, 1]
		"Feb" : [1]
		"July": [1]

map1 -> "Jan" : 2
		"Feb" : 1
		"July": 1

Employee e

e.getAddress().getCity()

Optional<Employee> e

e.get().getAddress().getCity().orElse(null);


CREATE TABLE employee(  
  empid		INT  PRIMARY KEY,  
  name		VARCHAR(10) NOT NULL,  
  job		VARCHAR(9) NULLABLE, 
  ssn       VARCHAR(10) Unique, 
  manager	INT,  
  hiredate	DATE,  
  salary	DECIMAL(7,2),
  deptid	INT,  
  CONSTRAINT pk_emp PRIMARY KEY (empid),
  CONSTRAINT fk_emp FOREIGN KEY (deptId) REFERENCES Department (deptid),
);



instace = object --> generic term for all "new constructor" generated "object"
POJO -- Plain old java object --> field, getter, setter, consturctor, tostring, equals, hashcode
Java Bean -- Spring

EmployeeHelper{
	public double addEmployeeSalary(e1, e2){
		return e1.salary+e.salary;
	}
}


https://www.walmart.com/browse/resident-evil-village-for-xbox/0/0/?_refineresult=true&_be_shelf_id=5685424&search_sort=100&facet=shelf_id:5685424



/user/1/load ? minAge=20

/user/login

>HEADER: timeStamp: 1/1/32335
>Body:
>username: Jack
>password: IRCA

@Controller
@RequestMapping(value = "/user")
public class UserController{

	@RequestMapping(value = "/{userId}/load" method = RequestMethod.GET)
	@GetMapping("/{userId}/load")
	public User getUser(@PathVariable("userId") int id, 
						@RequestParam("minAge") int minAge){
		UserService service = new UserService();
		return Service.getUser(id, minAge);
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	@PostMapping("/login")
	public User login(@RequestHeader("timestamp") TimeStamp time,
					 @RequestBody LoginUser loginUser
						){
		return service.getLoginUser(name, password);
	}

	//user/add  {name:'', address:''}
	@RequestMapping(value = "/add", method= RequestMethod.POST)
	@PostMapping("/login")
	public void addUser(@RequestBody User u){
		service.addNewUser(u);
	}

}

class LoingUser{
	username
	password
}

class User{
	name
	address
}

/product/100
/product/all

@Controller
@RequestMapping(value = "/product")
public class ProductController{

}