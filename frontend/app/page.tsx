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
    <main className="min-h-screen flex items-center justify-center">
      <div className="bg-[var(--quizz-background)] p-10 rounded-2xl shadow-xl w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold mb-8">Welcome to Quizz</h1>

        {/* Buttons */}
        <div className="flex gap-4 justify-center mb-4">
          <Link
            href="/login"
            className="bg-blue-500 hover:bg-blue-600 text-yellow-300 font-semibold px-6 py-3 rounded-lg transition"
          >
            Log in
          </Link>

          <Link
            href="/signup"
            className="bg-blue-500 hover:bg-blue-600 text-yellow-300 font-semibold px-6 py-3 rounded-lg transition"
          >
            Sign Up
          </Link>
        </div>

        <Link
          href="/join"
          className="inline-block bg-blue-600 hover:bg-blue-700 text-yellow-300 font-semibold px-10 py-3 rounded-lg transition"
        >
          Join a Game
        </Link>
      </div>
    </main>
  );
}
