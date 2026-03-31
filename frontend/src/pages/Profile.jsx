import React, { useState } from 'react'
import { useAuth } from '../context/AuthContext'

const Profile = () => {
  const { employee } = useAuth()
  const [isEditing, setIsEditing] = useState(false)
  const [formData, setFormData] = useState({
    name: employee?.name || '',
    email: employee?.email || '',
    phone: employee?.phone || '+1-234-567-8900',
    department: employee?.department || 'Sales',
    role: employee?.role || 'Sales Manager',
    joinDate: employee?.joinDate || '2023-01-15',
  })

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    // Send updated profile to API
    alert('Profile updated successfully!')
    setIsEditing(false)
  }

  return (
    <div className="flex-1 p-8">
      <h1 className="text-4xl font-bold text-gray-800 mb-8">Employee Profile</h1>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* Profile Card */}
        <div className="lg:col-span-1">
          <div className="bg-white rounded-lg shadow p-6 text-center">
            <div className="w-24 h-24 bg-gradient-to-br from-accent to-primary rounded-full mx-auto mb-4 flex items-center justify-center text-5xl">
              👤
            </div>
            <h2 className="text-2xl font-bold text-gray-800">{formData.name}</h2>
            <p className="text-gray-600 text-lg">{formData.role}</p>
            <div className="mt-4 space-y-2 text-sm">
              <p className="text-gray-600"><strong>Department:</strong> {formData.department}</p>
              <p className="text-gray-600"><strong>Email:</strong> {formData.email}</p>
              <p className="text-gray-600"><strong>Phone:</strong> {formData.phone}</p>
              <p className="text-gray-600"><strong>Joined:</strong> {formData.joinDate}</p>
            </div>
          </div>
        </div>

        {/* Edit Form */}
        <div className="lg:col-span-2">
          <div className="bg-white rounded-lg shadow p-6">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-2xl font-bold text-gray-800">
                {isEditing ? 'Edit Profile' : 'Profile Information'}
              </h2>
              <button
                onClick={() => setIsEditing(!isEditing)}
                className="px-4 py-2 bg-accent text-white rounded-lg hover:bg-blue-700 transition"
              >
                {isEditing ? 'Cancel' : 'Edit'}
              </button>
            </div>

            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Full Name</label>
                  <input
                    type="text"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Email</label>
                  <input
                    type="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Phone</label>
                  <input
                    type="tel"
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Department</label>
                  <input
                    type="text"
                    name="department"
                    value={formData.department}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Role</label>
                  <input
                    type="text"
                    name="role"
                    value={formData.role}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
                <div>
                  <label className="block text-gray-700 font-semibold mb-2">Join Date</label>
                  <input
                    type="date"
                    name="joinDate"
                    value={formData.joinDate}
                    onChange={handleChange}
                    disabled={!isEditing}
                    className="w-full px-4 py-2 border border-gray-300 rounded-lg disabled:bg-gray-50 disabled:text-gray-600"
                  />
                </div>
              </div>

              {isEditing && (
                <button
                  type="submit"
                  className="w-full bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-lg transition"
                >
                  Save Changes
                </button>
              )}
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Profile
