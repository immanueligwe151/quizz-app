import { redirect } from "next/navigation";
import { cookies } from "next/headers";
import Link from "next/link";

import Image from "next/image";

export default async function Home() {
  const cookieStore = await cookies();
  const token = cookieStore.get("auth_token");
  const role = cookieStore.get("role")?.value;

  if (token && role === "TEACHER") {
    redirect("/teacher/dashboard");
  }

  return (
    <main className="min-h-screen flex flex-col flex-start items-center  bg-[var(--quizz-background)]">
      <div className="w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold text-5xl">Welcome to Quizz</h1>
      </div>

      {/* Buttons */}
      <div className="flex flex-col md:flex-row flex-none flex-wrap gap-8 mb-4">
        <Link
          href="/login"
          className="bg-[var(--quizz-button-colour)] hover:bg-[var(--quizz-button-hover-colour)] text-[var(--quizz-text-colour)] font-semibold px-6 py-3 rounded-lg transition"
        >
          Log in
        </Link>

        <Link
          href="/signup"
          className="bg-[var(--quizz-button-colour)] hover:bg-[var(--quizz-button-hover-colour)] text-[var(--quizz-text-colour)] font-semibold px-6 py-3 rounded-lg transition"
        >
          Sign Up
        </Link>

        <Link
          href="/join"
          className="bg-[var(--quizz-button-colour)] hover:bg-[var(--quizz-button-hover-colour)] text-[var(--quizz-text-colour)] font-semibold px-6 py-3 rounded-lg transition"
        >
          Join a Game
        </Link>
      </div>
    </main>
  );
}
