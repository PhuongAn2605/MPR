package a1_1801040169;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview A HighEarner is a wealthy customers whose income are above 
 * 				a given threshold.
 * 
 * @attributes
 * 	income		Float		float
 * 
 * @objects
 * 	A typical HighEarner is <i,n,p,a,c> where i(id), n(name), p(phoneNumber),
 * 		a(address), c(income)
 * 
 * @abstract_properties
 *	mutable(income)=true /\ optional(income)=false /\ min(income)=10000000
 * @author phuongan
 *
 */
public class HighEarner extends Customer{
	@DomainConstraint(type="Float", mutable=true, optional=false, min=MIN_INCOME)
	private float income;
	
	private static final float MIN_INCOME=10000000;
	private static final int MIN_ID=10000000;
	

	/**
	 * @effects
	 * 	if i,n,p,a,c are valid
	 * 		initialise this as HighEarner:<i,n,p,a,c>
	 * 	else
	 * 		throw NotPossibleException
	 */
	public HighEarner(@AttrRef("id") int i,
			@AttrRef("name") String n,
			@AttrRef("phoneNumber") String p,
			@AttrRef("address") String a,
			@AttrRef("income") float c) 
		throws NotPossibleException {
		super(i,n,p,a);
		if(!validateIncome(c)) {
			throw new NotPossibleException("HighEarner<init>: invalid income: " + c);
		}else {
			income = c;
		}
	}
	
	
	/**
	 * @effects
	 * 	if income is valid
	 * 		set this.income = income
	 * 	else
	 * 		throws NotPossibleException
	 * @param income
	 * @return
	 */
	@DOpt(type=OptType.Mutator)@AttrRef("income")
	public void setIncome(float income) throws NotPossibleException {
		if(validateIncome(income)) {
			this.income = income;
		}else {
			throw new NotPossibleException("HighEarner.setIncome: invalid income: " + income);
		}
	}
	/**
	 * @effects
	 * 	return this.income
	 * @param income
	 * @return
	 */
	@DOpt(type=OptType.Observer)@AttrRef("income")
	public float getIncome() {
		return income;
	}
	
	/**
	 * @effects
	 * 	if income is valid
	 * 		return true
	 * 	else
	 * 		return false
	 * @param income
	 * @return
	 */
	protected boolean validateIncome(float i) {
		if(i >= MIN_INCOME) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @effects
	 * 	if id is valid
	 * 		return true
	 * 	else
	 * 		return false
	 */
	@Override
	@DomainConstraint(type="Integer", min=MIN_ID, optional=false)
	protected boolean validateId(int i) {
		if(i >= MIN_ID) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * if this satisfies the abstract properties
	 * 	return true
	 * else
	 * 	return false
	 */
	@Override
	public boolean repOK() {
		return super.repOK() && validateIncome(income);
	}
	
	@Override
	public String toString() {
		if(repOK()) {
			return super.toString() + "\nIncome: " + getIncome();
		}else {
			return "High Earner: {}";
		}
	}
}
