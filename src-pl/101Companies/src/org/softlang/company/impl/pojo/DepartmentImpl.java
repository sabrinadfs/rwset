package org.softlang.company.impl.pojo;

import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.softlang.company.Department;
import org.softlang.company.Employee;
import org.softlang.company.Subunit;
import org.softlang.visitor.ReturningVisitor;
import org.softlang.visitor.VoidVisitor;


public class DepartmentImpl extends ContainerImpl implements Department {
	
	//#if GUI
	private List<Department> subdepts;
	private List<Employee> employees;
	private DefaultMutableTreeNode treeNode;
	public DepartmentImpl() {
		super();
		subdepts = new LinkedList<Department>();
		employees = new LinkedList<Employee>();
	}	
	public void setSubdepts(List<Department> subdepts) {
		this.subdepts = subdepts;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Department> getSubdepts() {
		return subdepts;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setTreeNode(DefaultMutableTreeNode treeNode) {
		this.treeNode = treeNode;
	}
	public DefaultMutableTreeNode getTreeNode() {
		return treeNode;
	}
	public boolean add(Subunit u) {
		super.add(u);
		if(u instanceof Department){
			return this.subdepts.add((Department) u);
		}else if(u instanceof Employee){
			return this.employees.add((Employee) u);
		}
		return false;
	}
	/**
	 * This method returns the name for the tree-view.
	 */
	@Override
	public String toString() {
		String treeName = this.getName();
		return treeName;
	}
	//#endif
	
	
	public void accept(VoidVisitor v) {
		//#if Cut || Total
		v.visit(this);
		//#endif
	}
	public <R> R accept(ReturningVisitor<R> v) {
		//#if Cut || Total
		return v.visit(this);
		//#endif
	}
	
	
	public Employee getManager() {
		for (Subunit u : subunits())
			if (u instanceof Employee) {
				Employee e = (Employee)u;
				if (e.getManager()){
					//#if GUI
					employees.remove(e);
					//#endif
					return e;
				}
			}
		return null;
	}	
}
