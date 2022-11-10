package br.ufms.controle_venda.services;

import br.ufms.controle_venda.dtos.ClienteDto;
import br.ufms.controle_venda.models.Cliente;
import br.ufms.controle_venda.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Transactional
    public void newCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteDto clienteDto) {

        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        cliente.ifPresent(atualizandoCliente -> {
          BeanUtils.copyProperties(clienteDto, atualizandoCliente);
            });
        return cliente.get();
}

        public Page<Cliente> findAll(Pageable page){
            return clienteRepository.findAll(page);
        }


        public Optional<Cliente> findById(Long id) {
            return clienteRepository.findById(id);
        }
}
