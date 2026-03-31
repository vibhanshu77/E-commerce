import React, { useState, useEffect } from 'react'

const Customers = () => {
  const [customers, setCustomers] = useState([])
  const [searchTerm, setSearchTerm] = useState('')

  useEffect(() => {
    // Fetch customers from API
    // Mock data for demo
    setCustomers([
      { id: 1, name: 'John Doe', email: 'john@example.com', phone: '+1-234-567-8900', totalOrders: 12, totalSpent: 4250, joinDate: '2023-01-15' },
      { id: 2, name: 'Jane Smith', email: 'jane@example.com', phone: '+1-234-567-8901', totalOrders: 8, totalSpent: 2100, joinDate: '2023-03-20' },
      { id: 3, name: 'Bob Johnson', email: 'bob@example.com', phone: '+1-234-567-8902', totalOrders: 15, totalSpent: 5870, joinDate: '2022-11-10' },
      { id: 4, name: 'Alice Williams', email: 'alice@example.com', phone: '+1-234-567-8903', totalOrders: 5, totalSpent: 1200, joinDate: '2024-01-05' },
      { id: 5, name: 'Michael Brown', email: 'michael@example.com', phone: '+1-234-567-8904', totalOrders: 20, totalSpent: 7650, joinDate: '2022-06-12' },
    ])
  }, [])

  const filteredCustomers = customers.filter(customer =>
    customer.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    customer.email.toLowerCase().includes(searchTerm.toLowerCase())
  )

  return (
    <div className="flex-1 p-8">
      <h1 className="text-4xl font-bold text-gray-800 mb-8">Customer Management</h1>

      {/* Summary Stats */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div className="bg-gradient-to-br from-blue-500 to-blue-600 rounded-lg shadow p-6 text-white">
          <p className="text-blue-100">Total Customers</p>
          <p className="text-4xl font-bold mt-2">{customers.length}</p>
        </div>
        <div className="bg-gradient-to-br from-green-500 to-green-600 rounded-lg shadow p-6 text-white">
          <p className="text-green-100">Total Spent</p>
          <p className="text-4xl font-bold mt-2">${customers.reduce((sum, c) => sum + c.totalSpent, 0)}</p>
        </div>
        <div className="bg-gradient-to-br from-purple-500 to-purple-600 rounded-lg shadow p-6 text-white">
          <p className="text-purple-100">Average Orders per Customer</p>
          <p className="text-4xl font-bold mt-2">
            {(customers.reduce((sum, c) => sum + c.totalOrders, 0) / customers.length).toFixed(1)}
          </p>
        </div>
      </div>

      {/* Search Bar */}
      <div className="bg-white rounded-lg shadow p-6 mb-6">
        <input
          type="text"
          placeholder="Search customers by name or email..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-accent"
        />
      </div>

      {/* Customers Table */}
      <div className="bg-white rounded-lg shadow overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-gray-200 bg-gray-50">
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Customer Name</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Email</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Phone</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Orders</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Total Spent</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Join Date</th>
                <th className="text-left py-4 px-6 font-semibold text-gray-700">Action</th>
              </tr>
            </thead>
            <tbody>
              {filteredCustomers.map(customer => (
                <tr key={customer.id} className="border-b border-gray-100 hover:bg-gray-50">
                  <td className="py-4 px-6">
                    <div className="flex items-center">
                      <div className="w-10 h-10 bg-gradient-to-br from-accent to-primary rounded-full flex items-center justify-center text-white font-bold mr-3">
                        {customer.name.charAt(0)}
                      </div>
                      <span className="font-semibold text-gray-800">{customer.name}</span>
                    </div>
                  </td>
                  <td className="py-4 px-6 text-gray-600">{customer.email}</td>
                  <td className="py-4 px-6 text-gray-600">{customer.phone}</td>
                  <td className="py-4 px-6">
                    <span className="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm font-semibold">
                      {customer.totalOrders}
                    </span>
                  </td>
                  <td className="py-4 px-6 font-semibold text-accent">${customer.totalSpent}</td>
                  <td className="py-4 px-6 text-gray-600">{customer.joinDate}</td>
                  <td className="py-4 px-6">
                    <button className="text-accent hover:text-blue-700 font-semibold">
                      View →
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {filteredCustomers.length === 0 && (
          <div className="text-center py-12 text-gray-500">
            No customers found
          </div>
        )}
      </div>
    </div>
  )
}

export default Customers
