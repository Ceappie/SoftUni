//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace _05.Employees_by_Department_and_Manager
{
    using System;
    using System.Collections.Generic;
    
    public partial class Employee
    {
        public Employee()
        {
            this.ManagedDepartments = new HashSet<Department>();
            this.ManagedEmployees = new HashSet<Employee>();
            this.Projects = new HashSet<Project>();
        }
    
        public int EmployeeID { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string MiddleName { get; set; }
        public string JobTitle { get; set; }
        public int DepartmentID { get; set; }
        public Nullable<int> ManagerID { get; set; }
        public System.DateTime HireDate { get; set; }
        public decimal Salary { get; set; }
        public Nullable<int> AddressID { get; set; }
    
        public virtual Address Address { get; set; }
        public virtual ICollection<Department> ManagedDepartments { get; set; }
        public virtual Department Department { get; set; }
        public virtual ICollection<Employee> ManagedEmployees { get; set; }
        public virtual Employee Manager { get; set; }
        public virtual ICollection<Project> Projects { get; set; }
    }
}