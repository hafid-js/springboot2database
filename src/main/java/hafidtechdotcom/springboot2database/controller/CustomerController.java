package hafidtechdotcom.springboot2database.controller;

import hafidtechdotcom.springboot2database.entity.Customer;
import hafidtechdotcom.springboot2database.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    ICustomerRepo customerRepo;
    @PostMapping("/customers")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> list = customerRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getSingleCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isPresent()) {
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<Customer>(customerRepo.save(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if(customer.isPresent()) {
                customerRepo.delete(customer.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
