"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";

import { redirect } from "next/navigation";
//import { cookies } from "next/headers";
import Link from "next/link";
import FloatingInput from "@/app/components/FloatingInput";

import Image from "next/image";

export default function Home() {
  const router = useRouter();
  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e: any) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: "include", // Include cookies in the request
        body: JSON.stringify({
          username: form.username, //maps to backend DTO
          password: form.password,
        }),
      });

      if (res.ok) {
        // redirects to teacher dashboard after successful login
        const data = await res.json();
        alert(`Welcome, ${data.teacherName}! Your username is ${data.username}.`);

        router.push("/teacher/dashboard");
      } else {
        const error = await res.text();
        alert(error);
      }
    } catch (err) {
      console.error("Login failed", err);
      alert(err);
    }
  };

  return (
    <main className="min-h-screen flex flex-col flex-start items-center  bg-[var(--quizz-background)]">
      <div className="w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold text-5xl">Log in</h1>
      </div>

      <form className="space-y-6 margin-top-8" onSubmit={handleSubmit}>
        <div className="flex flex-row md:flex-row flex-none flex-wrap gap-8 mb-4">
            <FloatingInput
            label="Username"
            name="username"
            onChange={handleChange}
          />

          <FloatingInput
            label="Password"
            name="password"
            onChange={handleChange}
            type="password"
          />
        </div>
          

          <button
            type="submit"
            className="bg-[var(--quizz-button-colour)] hover:bg-[var(--quizz-button-hover-colour)] text-[var(--quizz-text-colour)] font-semibold px-6 py-3 rounded-lg transition"
          >
            Login
          </button>
        </form>
    </main>
  );
}
