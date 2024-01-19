package larionovoleksanr.exam.controllers;

import larionovoleksanr.exam.entities.Dipendente;
import larionovoleksanr.exam.entities.Dispositivo;
import larionovoleksanr.exam.exceptions.BadRequestException;
import larionovoleksanr.exam.payloads.NewDeviceDTO;
import larionovoleksanr.exam.services.DeviceService;
import larionovoleksanr.exam.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo saveBlogpost(@RequestBody @Validated NewDeviceDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Ci sono errori nel payload!");
        }

        Dispositivo device = new Dispositivo();
        device.setDeviceType(payload.deviceType());
        device.setStateOfDevice(payload.stateOfDevice());

        Long employeeId = payload.idEmployee();
        if (employeeId != null) {
            Dipendente employee = employeeService.findById(employeeId);
            device.setEmployee(employee);
        }

        return deviceService.saveDevice(device);
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
