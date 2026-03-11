type FloatingInputProps = {
  label: string;
  type?: string;
  name: string;
  onChange?: (e: any) => void;
};

export default function FloatingInput({
  label,
  type = "text",
  name,
  onChange,
}: FloatingInputProps) {
  return (
    <div className="relative w-full">
      <input
        type={type}
        name={name}
        id={name}
        placeholder=" "
        onChange={onChange}
        className="
          peer
          w-full
          rounded-lg
          border-2
          border-[#A99700]
          bg-[#D9D9D9]
          px-4
          py-3
          text-black
          focus:border-yellow-400
          focus:outline-none
        "
      />

      <label
        htmlFor={name}
        className="
          absolute
          left-4
          top-3
          text-[#393A3B]
          transition-all

          peer-placeholder-shown:top-3
          peer-placeholder-shown:text-base

          peer-focus:-top-2
          peer-focus:text-sm
          peer-focus:text-[#FFEF6A]
          peer-focus:bg-slate-700

          peer-not-placeholder-shown:-top-2
          peer-not-placeholder-shown:text-sm
          peer-not-placeholder-shown:text-[#FFEF6A]
          peer-not-placeholder-shown:bg-slate-700
          px-1
        "
      >
        {label}
      </label>
    </div>
  );
}
