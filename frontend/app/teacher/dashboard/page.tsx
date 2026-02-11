import { redirect } from "next/navigation";
import { cookies } from "next/headers";
import Link from "next/link";

import Image from "next/image";
//placeholder code for teacher dashboard page, will be updated with actual content later

export default function Home() {  
  return (
    <main className="min-h-screen flex flex-col flex-start items-center  bg-[var(--quizz-background)]">
      <div className="w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold text-5xl">Welcome to Quizz</h1>
        <h3 className="text-[var(--quizz-text-colour)] text-lg mt-4">The ultimate quiz game for teachers and students!</h3>
      </div>

    </main>
  );
}
