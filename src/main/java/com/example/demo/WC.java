package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController

public class WC {
    ArrayList<Worker> workers = new ArrayList<>();
    @PostMapping("/workers")
    public Worker addWorker(@RequestBody Worker w)
    {
        workers.add(w);
        return w;
    }

    @GetMapping("/workers")

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    @GetMapping("/workers/{id}")
    public Worker getWorkerById(@PathVariable int id) {
        for (Worker w : workers) {
            if (w.getId() == id) {
                return w;
            }
        }
        return null;
    }

    @PutMapping("/workers/{id}")
    public String updateWorker(@PathVariable int id, @RequestBody Worker updatedWorker)
    {
        for (Worker w : workers)
        {
            if (w.getId() == id)
            {
                w.setName(updatedWorker.getName());
                w.setDepartment(updatedWorker.getDepartment());
                return "Worker Updated Successfully";
            }
        }
        return "Worker Not Found";
    }

    @DeleteMapping("/workers/{id}")
    public String deleteWorker(@PathVariable int id)
    {
        workers.removeIf(w -> w.getId() == id);
        return "Worker Deleted Successfully";
    }
}
