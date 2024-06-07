package com.github.mdandretto.microservices.messagingconsumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.mdandretto.microservices.messagingconsumer.domain.Item;
import com.github.mdandretto.microservices.messagingconsumer.domain.ItemList;
import com.github.mdandretto.microservices.messagingconsumer.domain.Transaction;
import com.github.mdandretto.microservices.messagingconsumer.domain.User;
import com.github.mdandretto.microservices.messagingconsumer.repository.ListRepository;
import com.github.mdandretto.microservices.messagingconsumer.repository.UserRepository;


@Service
public class ConsumerService {

    private final UserRepository userRepository;
    private final ListRepository listRepository;
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    public ConsumerService(UserRepository userRepository, ListRepository listRepository) {
        this.userRepository = userRepository;
        this.listRepository = listRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user) {
        Item[] itens = user.getItem();
        float totalValue = 0;
        
        if(itens.length > 0)
        {
            for(int i = 0; i < itens.length; i++)
            {
                totalValue = totalValue + (itens[i].getPreco()*itens[i].getQuantidade());
                ItemList itemList = new ItemList();
                itemList.setCodigoPedido(user.getCodigoPedido());
                itemList.setPreco(itens[i].getPreco());
                itemList.setProduto(itens[i].getProduto());
                itemList.setQuantidade(itens[i].getQuantidade());
                ItemList save = listRepository.save(itemList);
                logger.info("persisted " + save);
            }
            
        }

        Transaction tr = new Transaction();
        tr.setCodigoCliente(user.getCodigoCliente());
        tr.setCodigoPedido(user.getCodigoPedido());

        String total=String.valueOf(totalValue);
        tr.setTotalAmmount(total);

        Transaction save = userRepository.save(tr);
        logger.info("persisted " + save);
        //logger.info("User received: " + tr);
    }

}

