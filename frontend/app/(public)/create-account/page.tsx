import { redirect } from "next/navigation";
import { cookies } from "next/headers";
import Link from "next/link";
import FloatingInput from "@/app/components/FloatingInput";

import Image from "next/image";

export default function Home() {
  return (
    <main className="min-h-screen flex flex-col flex-start items-center  bg-[var(--quizz-background)]">
      <div className="w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold text-5xl">Create an account</h1>
      </div>

      <form className="space-y-6 margin-top-8">
        <div className="flex flex-row md:flex-row flex-none flex-wrap gap-8 mb-4">
            <FloatingInput
            label="Full name"
            name="fullname"
          />

          <FloatingInput
            label="Username"
            name="username"
          />

          <FloatingInput
            label="Email address"
            name="email"
            type="email"
          />

          <FloatingInput
            label="Enter a password"
            name="password1"
            type="password"
          />

          <FloatingInput
            label="Re-enter password"
            name="password2"
            type="password"
          />
        </div>
          

          <button
            type="submit"
            className="bg-[var(--quizz-button-colour)] hover:bg-[var(--quizz-button-hover-colour)] text-[var(--quizz-text-colour)] font-semibold px-6 py-3 rounded-lg transition"
          >
            Create Account
          </button>
        </form>
    </main>
  );
}
