package larionovoleksanr.exam.controllers;

import larionovoleksanr.exam.entities.Dipendente;
import larionovoleksanr.exam.entities.Dispositivo;
import larionovoleksanr.exam.payloads.NewDeviceDTO;
import larionovoleksanr.exam.services.DeviceService;
import larionovoleksanr.exam.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<Dispositivo> getDevices(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "0") int size,
                                        @RequestParam(defaultValue = "id") String orderBy) {
        return deviceService.getDevices(page, size, orderBy);
    }

    @PostMapping
    public Dispositivo saveBlogpost(@RequestBody NewDeviceDTO payload) {
        if (payload.idEmployee() == null) {
            Dispositivo device = new Dispositivo();
            device.setDeviceType(payload.type());
            device.setStateOfDevice(payload.stateOfDevice());
            return deviceService.saveDevice(device);
        } else {
            Long employeeId = payload.idEmployee();
            Dipendente employee = employeeService.findById(employeeId);
            Dispositivo device = new Dispositivo();
            device.setEmployee(employee);
            device.setDeviceType(payload.type());
            device.setStateOfDevice(payload.stateOfDevice());
            return deviceService.saveDevice(device);
        }
    }

    @GetMapping("/{id}")
    public Dispositivo findById(@PathVariable Long id) {
        return deviceService.findById(id);
    }

    @PutMapping("/{id}")
    public Dispositivo findByIdAndUpdate(@PathVariable Long id,
                                         @RequestBody Dispositivo body,
                                         @RequestBody Dipendente employee) {
        return deviceService.findByIdAndUpdate(id, body, employee);
    }

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable Long id) {
        deviceService.delete(id);
    }
}
