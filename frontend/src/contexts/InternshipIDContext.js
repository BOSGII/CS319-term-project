import { createContext, useState } from "react";

export const InternshipIDContext = createContext();

export function InternshipIDProvider({ children }) {
  // Initialize state variables for the user's ID and role
  const [internshipId, setInternshipId] = useState("");

  return (
    <InternshipIDContext.Provider value={{ internshipId, setInternshipId }}>
      {children}
    </InternshipIDContext.Provider>
  );
}
