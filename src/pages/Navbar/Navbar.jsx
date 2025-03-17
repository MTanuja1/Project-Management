import React from "react";
import { Dialog, DialogHeader } from "@/components/ui/dialog";
import { DialogTrigger, DialogContent } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import CreateForm from "../Project/CreateForm";
import {
  DropdownMenu,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { PersonIcon } from "@radix-ui/react-icons";
import { DropdownMenuContent } from "@/components/ui/dropdown-menu";
const Navbar = () => {
  return (
    <div className="border-b py-4 px-5 flex items-center  justify-between">
      <div className="flex items-center gap-3">
        <p className="cursor-pointer">Project Management</p>
        <Dialog>
          <DialogTrigger>
            <Button variant="ghost">New Project</Button>
          </DialogTrigger>
          <DialogContent className="bg-cyan-950">
            <DialogHeader >Create New Project</DialogHeader>
            <CreateForm />
          </DialogContent>
        </Dialog>
        <Button variant="ghost">Upgrade</Button>
      </div>
      <div className="flex gap-3 items-center">
        <DropdownMenu>
          <DropdownMenuTrigger>
            <Button variant="outline" size="icon" className="rounded-full border-2 border-gray-500">
              <PersonIcon />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent>
            <DropdownMenuItem>Logout</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
        <p>Tanuja</p>
      </div>
    </div>
  );
};

export default Navbar;
