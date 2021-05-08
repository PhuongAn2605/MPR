package a1_1801040169;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview A Customer is a person who is of interest to the shop
 * 
 * @attributes
 * id			Integer		int
 * name			String		
 * phoneNumber	String
 * address		String
 * 
 * @objects
 *  A typical Customer is <i,n,p,a> where i(id), n(name), 
 *  	p(phoneNumber), a(address)
 * 
 * @abstract_properties
 * 	mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ max(id)=1000000000 /\
 *  mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 *  mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\
 *  mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author phuongan
 *
 */
public class Customer implements Comparable{
	@DomainConstraint(type="Integer", mutable=false, optional=false, min=MIN_ID, max=MAX_ID)
	private int id;
	@DomainConstraint(type="String", mutable=true, optional=false, length=LEN_NAME)
	private String name;
	@DomainConstraint(type="String", mutable=true, optional=false, length=LEN_PHONE)
	private String phoneNumber;
	@DomainConstraint(type="String", mutable=true, optional=false, length=LEN_ADD)
	private String address;
	
	private static final int MIN_ID=1;
	private static final int MAX_ID=1000000000;
	private static final int LEN_NAME=50;
	private static final int LEN_PHONE=10;
	private static final int LEN_ADD=100;
	
	/**
	 * @effects
	 * 	if i, n, p, a are valid
	 * 		initialise this as Customer:<i, n, p, a>
	 * 	else
	 * 		throw NotPossibleException
	 */
	public Customer(@AttrRef("id") int i,
			@AttrRef("name") String n,
			@AttrRef("phoneNumber") String p,
			@AttrRef("address") String a) throws NotPossibleException {
		if(validateId(i)) {
			id = i;
		}else {
			throw new NotPossibleException("Customer<init>: invalid id: " + i);
		}
		if(validateName(n)) {
			name = n;
		}else {
			throw new NotPossibleException("Customer<init>: invalid name: " + n);
		}
		if(validatePhoneNumber(p)) {
			phoneNumber = p;
		}else {
			throw new NotPossibleException("Customer<init>: invalid phone number: " + p);
		}
		if(validateAddress(a)) {
			address = a;
		}else {
			throw new NotPossibleException("Customer<init>: invalid address: " + a);
		}
	}
	
	
	/**
	 * @effects
	 * 	if name is valid
	 * 		set this.name = name
	 * 	else
	 * 		throws NotPossibleException
	 * @param name
	 * @return
	 */
	@DOpt(type=OptType.Mutator)@AttrRef("name")
	public void setName(String name) throws NotPossibleException {
		if(validateName(name)) {
			this.name = name;
		}
		else {
			throw new NotPossibleException("Customer.setName: invalid name: " + name);
		}
	}
	
	/**
	 * @effects
	 * 	if phoneNumber is valid
	 * 		set this.phoneNumber = phoneNumber
	 * 	else
	 * 		throws NotPossibleException
	 * @param phoneNumber
	 * @return
	 */
	@DOpt(type=OptType.Mutator)@AttrRef("phoneNumber")
	public void setPhoneNumber(String phoneNumber) throws NotPossibleException {
		if(validatePhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}else {
			throw new NotPossibleException("Customer.setPhoneNumber: invalid phoneNumber: " + phoneNumber);
		}
	}
	
	/**
	 * @effects
	 * 	if address is valid
	 * 		set this.address = address
	 * 	else
	 * 		throws NotPossibleException
	 * @param address
	 * @return
	 */
	@DOpt(type=OptType.Mutator)@AttrRef("address")
	public void setAddress(String address) throws NotPossibleException {
		if(validateAddress(address)) {
			this.address = address;
		}else {
			throw new NotPossibleException("Customer.setAddress: invalid address " + address);
		}
	}
	
	/**
	 * @effects
	 * 	return this.id
	 */
	@DOpt(type=OptType.Observer)@AttrRef("id")
	public int getId() {
		return id;
	}
	
	/**
	 * @effects
	 * 	return this.name
	 */
	@DOpt(type=OptType.Observer)@AttrRef("name")
	public String getName() {
		return name;
	}
	
	/**
	 * @effects
	 * 	return this.phoneNumber
	 */
	@DOpt(type=OptType.Observer)@AttrRef("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @effects
	 * 	return this.address
	 */
	@DOpt(type=OptType.Observer)@AttrRef("address")
	public String getAddress() {
		return address;
	}
	
	/**
	 * @effects
	 *  if all abstract properties satisfied
	 *  	return true
	 *  else
	 *  	return false
	 * @return
	 */
	public boolean repOK() {
		return validate(id, name, phoneNumber, address);
	}
	
	/**
	 * @effects
	 * 	if <i,n,p,a> is a valid tuple
	 * 		return true
	 * 	else
	 * 		return false
	 */
	private boolean validate(int i, String n, String p, 
			String a) {
		return validateId(i) && validateName(n) && validatePhoneNumber(p)
				&& validateAddress(a);
	}
	
	/**
	 * @effects
	 * 	if i is valid
	 * 		return true
	 *  else
	 *  	return false
	 * @param i
	 * @return
	 */
	protected boolean validateId(int i) {
		if(i >= MIN_ID && i <= MAX_ID) {
			return true;
		}
		return false;
	}
	
	/**
	 * @effects
	 * 	if n is valid
	 * 		return true
	 *  else
	 *  	return false
	 * @param n
	 * @return
	 */
	private boolean validateName(String n) {
		if(n == null || n.trim().isEmpty()) {
			return false;
		}
		if(n.length() > LEN_NAME) {
			return false;
		}
		return true;
	}
	
	/**
	 * @effects
	 * 	if p is valid
	 * 		return true
	 *  else
	 *  	return false
	 * @param p
	 * @return
	 */
	private boolean validatePhoneNumber(String p) {
		if(p == null || p.trim().isEmpty()) {
			return false;
		}
		if(p.length() > LEN_PHONE) {
			return false;
		}
		return true;
	}
	
	/**
	 * @effects
	 * 	if a id valid
	 * 		return true
	 *  else
	 *  	return false
	 * @param a
	 * @return
	 */
	private boolean validateAddress(String a) {
		if(a == null || a.trim().isEmpty()) {
			return false;
		}
		if(a.length() > LEN_ADD) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Object o) {
		return compareByName(o);
	}
	
	/**
	 * @effects
	 *           if o is null 
	 *              throw NullPointerException 
	 *           else if o is not a Customer object
	 *              throw ClassCastException
	 *            else 
	 *              return this.name.compareTo(o.name)
	 */
	protected int compareByName(Object o) 
			throws NullPointerException, ClassCastException {
		
		if (o == null) {
			throw new NullPointerException("Customer.compareByName");
		}else if(!(o instanceof Customer)) {
			throw new ClassCastException ("Customer.compareByName: not a Customer " + o);
		}
		Customer c = (Customer) o;
		return this.name.compareTo(c.name);
	}
	
	@Override
	public String toString() {
		if (validate(id, name, phoneNumber, address)) {
			return "Customer:\nId: " + getId() + "\nName: " + getName() + "\nPhone Number: "
					+ getPhoneNumber() + "\nAddress: " + getAddress() ;
		} else {
			return "Customer: {}";
		}
	}
}
