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
    name: "",
    username: "",
    email: "",
    password1: "",
    password2: "",
  });

  const handleChange = (e: any) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    if (form.password1 !== form.password2) {
      alert("Passwords do not match");
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/auth/signup", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: form.name,      // maps to backend DTO
          username: form.username,
          email: form.email,
          password: form.password1,
          confirmPassword: form.password2,
        }),
      });

      if (res.ok) {
        // redirects to login page after successful account creation
        router.push("/login");
      } else {
        const error = await res.text();
        alert(error);
      }
    } catch (err) {
      console.error(err);
      alert("Something went wrong");
    }
  };

  return (
    <main className="min-h-screen flex flex-col flex-start items-center  bg-[var(--quizz-background)]">
      <div className="w-[420px] text-center">
        {/* Heading */}
        <h1 className="text-[var(--quizz-text-colour)] font-bold text-5xl">Create an account</h1>
      </div>

      <form className="space-y-6 margin-top-8" onSubmit={handleSubmit}>
        <div className="flex flex-row md:flex-row flex-none flex-wrap gap-8 mb-4">
            <FloatingInput
            label="Full name"
            name="name"
            onChange={handleChange}
          />

          <FloatingInput
            label="Username"
            name="username"
            onChange={handleChange}
          />

          <FloatingInput
            label="Email address"
            name="email"
            type="email"
            onChange={handleChange}
          />

          <FloatingInput
            label="Enter a password"
            name="password1"
            type="password"
            onChange={handleChange}
          />

          <FloatingInput
            label="Re-enter password"
            name="password2"
            type="password"
            onChange={handleChange}
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
