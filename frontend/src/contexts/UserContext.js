import { createContext, useState } from "react";

export const UserContext = createContext();

export function UserProvider({ children }) {
  // Initialize state variables for the user's ID and role
  const [user, setUser] = useState({ id: null, role: null, sessionId: null});

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
}