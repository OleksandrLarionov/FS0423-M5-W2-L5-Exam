package larionovoleksanr.exam.services;


import larionovoleksanr.exam.entities.Dipendente;
import larionovoleksanr.exam.exceptions.BadRequestException;
import larionovoleksanr.exam.exceptions.NotFoundException;
import larionovoleksanr.exam.payloads.NewEmployeeDTO;
import larionovoleksanr.exam.repositories.DipendenteDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    DipendenteDAO dipendenteDAO;

    public Page<Dipendente> getEmployees(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return dipendenteDAO.findAll(pageable);
    }


    public Dipendente saveEmployee(NewEmployeeDTO body) {
        dipendenteDAO.findByEmail(body.email()).ifPresent(employee -> {
            throw new BadRequestException("L'email è gia in uso " + employee.getEmail());
        });
        Random rndm = new Random();
        Dipendente employee = new Dipendente();
        employee.setUsername(body.name() + "+" + body.surname() + rndm.nextInt(1, 100000));
        employee.setName(body.name());
        employee.setSurname(body.surname());
        employee.setEmail(body.email());
        return dipendenteDAO.save(employee);
    }

    public Dipendente findById(Long id) {
        return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id) {
        Dipendente found = this.findById(id);
        dipendenteDAO.delete(found);

    }

    public Dipendente findByIdAndUpdate(Long id, Dipendente body) {
        Dipendente found = this.findById(id);
        found.setId(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        return dipendenteDAO.save(found);
    }

}
