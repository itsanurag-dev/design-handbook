import { defineConfig } from "vitepress"

export default defineConfig({
  title: "Design Handbook",
  description: "Software Architecture, GoF Patterns, LLD & High-Level Distributed Systems",
  base: "/design-handbook/",
  themeConfig: {
    nav: [
      {
            "text": "Home",
            "link": "/"
      },
      {
            "text": "HLD Blueprints",
            "link": "/hld/url-shortener"
      },
      {
            "text": "Low-Level Designs",
            "link": "/lld/parkingslot/parkinglotservice"
      },
      {
            "text": "Concurrency",
            "link": "/concurrency/cache/lrucache"
      },
      {
            "text": "GoF Patterns",
            "link": "/patterns-overview"
      },
      {
            "text": "ADRs",
            "link": "/adr/0001-use-junit-5-jupiter"
      },
      {
            "text": "GitHub",
            "link": "https://github.com/itsanurag-dev/design-handbook"
      }
],
    sidebar: [
      {
            "text": "Overview",
            "items": [
                  {
                        "text": "Architecture & Curriculum",
                        "link": "/"
                  },
                  {
                        "text": "Design Patterns Overview",
                        "link": "/patterns-overview"
                  }
            ]
      },
      {
            "text": "High-Level Distributed Designs (HLD)",
            "collapsed": false,
            "items": [
                  {
                        "text": "Distributed URL Shortener (TinyURL)",
                        "link": "/hld/url-shortener"
                  },
                  {
                        "text": "Chat & Real-Time Messaging (WhatsApp)",
                        "link": "/hld/whatsapp"
                  },
                  {
                        "text": "Cloud Storage & Sync (Dropbox)",
                        "link": "/hld/dropbox"
                  },
                  {
                        "text": "Distributed Rate Limiter",
                        "link": "/hld/rate-limiter"
                  },
                  {
                        "text": "Social Network News Feed (Instagram)",
                        "link": "/hld/instagram"
                  },
                  {
                        "text": "Smart Parking Lot Facility (IoT)",
                        "link": "/hld/parking-lot"
                  }
            ]
      },
      {
            "text": "Concurrency Primitives",
            "collapsed": false,
            "items": [
                  {
                        "text": "Lock-Free LRU Cache",
                        "link": "/concurrency/cache/lrucache"
                  },
                  {
                        "text": "Token-Bucket Rate Limiter",
                        "link": "/concurrency/ratelimiter/tokenbucketratelimiter"
                  },
                  {
                        "text": "Twitter Snowflake ID Generator",
                        "link": "/concurrency/idgenerator/snowflakeidgenerator"
                  },
                  {
                        "text": "Consistent Hashing Router",
                        "link": "/concurrency/consistenthash/consistenthashrouter"
                  }
            ]
      },
      {
            "text": "Low-Level Designs (LLD)",
            "collapsed": false,
            "items": [
                  {
                        "text": "Parking Lot System",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Parkinglotservice",
                                    "link": "/lld/parkingslot/parkinglotservice"
                              },
                              {
                                    "text": "Parkingspot",
                                    "link": "/lld/parkingslot/parkingspot"
                              },
                              {
                                    "text": "Ticket",
                                    "link": "/lld/parkingslot/ticket"
                              },
                              {
                                    "text": "Vehicletype",
                                    "link": "/lld/parkingslot/vehicletype"
                              }
                        ]
                  },
                  {
                        "text": "Elevator System (LOOK/SCAN)",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Direction",
                                    "link": "/lld/elevator/direction"
                              },
                              {
                                    "text": "Elevatorstate",
                                    "link": "/lld/elevator/elevatorstate"
                              },
                              {
                                    "text": "Externalrequest",
                                    "link": "/lld/elevator/externalrequest"
                              },
                              {
                                    "text": "Internalrequest",
                                    "link": "/lld/elevator/internalrequest"
                              },
                              {
                                    "text": "Lookelevatorcontroller",
                                    "link": "/lld/elevator/lookelevatorcontroller"
                              },
                              {
                                    "text": "Request",
                                    "link": "/lld/elevator/request"
                              }
                        ]
                  },
                  {
                        "text": "Pub-Sub Message Broker",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Message",
                                    "link": "/lld/pubsub/message"
                              },
                              {
                                    "text": "Pubsubbroker",
                                    "link": "/lld/pubsub/pubsubbroker"
                              },
                              {
                                    "text": "Subscriber",
                                    "link": "/lld/pubsub/subscriber"
                              }
                        ]
                  },
                  {
                        "text": "URL Shortener (TinyURL)",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Shorturl",
                                    "link": "/lld/urlshortener/shorturl"
                              },
                              {
                                    "text": "Urlshortner",
                                    "link": "/lld/urlshortener/urlshortner"
                              }
                        ]
                  },
                  {
                        "text": "In-Memory File System",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Inmemoryfilesystem",
                                    "link": "/lld/filesystem/inmemoryfilesystem"
                              }
                        ]
                  },
                  {
                        "text": "Typeahead Autocomplete (Trie)",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Autocompletesearch",
                                    "link": "/lld/autocomplete/autocompletesearch"
                              }
                        ]
                  },
                  {
                        "text": "Cloud Storage & Chunking",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Chunkscalculator",
                                    "link": "/lld/dropbox/chunkscalculator"
                              },
                              {
                                    "text": "Documents",
                                    "link": "/lld/dropbox/documents"
                              },
                              {
                                    "text": "Node",
                                    "link": "/lld/dropbox/node"
                              },
                              {
                                    "text": "Offlinedata",
                                    "link": "/lld/dropbox/offlinedata"
                              },
                              {
                                    "text": "Onlinedata",
                                    "link": "/lld/dropbox/onlinedata"
                              }
                        ]
                  },
                  {
                        "text": "Chat & Messaging (WhatsApp)",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Dbconnectionutil",
                                    "link": "/lld/whatsapp/dbconnectionutil"
                              },
                              {
                                    "text": "Group",
                                    "link": "/lld/whatsapp/group"
                              },
                              {
                                    "text": "Messagingqueue",
                                    "link": "/lld/whatsapp/messagingqueue"
                              },
                              {
                                    "text": "Messenger",
                                    "link": "/lld/whatsapp/messenger"
                              },
                              {
                                    "text": "Receiver",
                                    "link": "/lld/whatsapp/receiver"
                              },
                              {
                                    "text": "Sender",
                                    "link": "/lld/whatsapp/sender"
                              },
                              {
                                    "text": "Socketconnectionutil",
                                    "link": "/lld/whatsapp/socketconnectionutil"
                              },
                              {
                                    "text": "Systemuser",
                                    "link": "/lld/whatsapp/systemuser"
                              },
                              {
                                    "text": "Systemutil",
                                    "link": "/lld/whatsapp/systemutil"
                              }
                        ]
                  },
                  {
                        "text": "Social Graph & Feed (Instagram)",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Connectioninvitation",
                                    "link": "/lld/instagram/connectioninvitation"
                              },
                              {
                                    "text": "Instagram",
                                    "link": "/lld/instagram/instagram"
                              }
                        ]
                  },
                  {
                        "text": "Tic-Tac-Toe Game Engine",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Tictactoe",
                                    "link": "/lld/tictactoe/tictactoe"
                              }
                        ]
                  },
                  {
                        "text": "Spreadsheet Formula Engine",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Excelsumformula",
                                    "link": "/lld/excel/excelsumformula"
                              }
                        ]
                  },
                  {
                        "text": "Distributed Task Scheduler",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Timer",
                                    "link": "/lld/timer/timer"
                              },
                              {
                                    "text": "Trigger",
                                    "link": "/lld/timer/trigger"
                              }
                        ]
                  }
            ]
      },
      {
            "text": "Creational Patterns",
            "collapsed": true,
            "items": [
                  {
                        "text": "Abstractfactory",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Abstractfactorydemo",
                                    "link": "/patterns/creational/abstractfactory/abstractfactorydemo"
                              },
                              {
                                    "text": "Chair",
                                    "link": "/patterns/creational/abstractfactory/chair"
                              },
                              {
                                    "text": "Furniturefactory",
                                    "link": "/patterns/creational/abstractfactory/furniturefactory"
                              },
                              {
                                    "text": "Modernchair",
                                    "link": "/patterns/creational/abstractfactory/modernchair"
                              },
                              {
                                    "text": "Modernfurniturefactory",
                                    "link": "/patterns/creational/abstractfactory/modernfurniturefactory"
                              },
                              {
                                    "text": "Moderntable",
                                    "link": "/patterns/creational/abstractfactory/moderntable"
                              },
                              {
                                    "text": "Table",
                                    "link": "/patterns/creational/abstractfactory/table"
                              },
                              {
                                    "text": "Victorianchair",
                                    "link": "/patterns/creational/abstractfactory/victorianchair"
                              },
                              {
                                    "text": "Victorianfurniturefactory",
                                    "link": "/patterns/creational/abstractfactory/victorianfurniturefactory"
                              },
                              {
                                    "text": "Victoriantable",
                                    "link": "/patterns/creational/abstractfactory/victoriantable"
                              }
                        ]
                  },
                  {
                        "text": "Builder",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Builderdemo",
                                    "link": "/patterns/creational/builder/builderdemo"
                              },
                              {
                                    "text": "Computer",
                                    "link": "/patterns/creational/builder/computer"
                              },
                              {
                                    "text": "Computerbuilder",
                                    "link": "/patterns/creational/builder/computerbuilder"
                              }
                        ]
                  },
                  {
                        "text": "Factory",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Circleshape",
                                    "link": "/patterns/creational/factory/circleshape"
                              },
                              {
                                    "text": "Factorydemo",
                                    "link": "/patterns/creational/factory/factorydemo"
                              },
                              {
                                    "text": "Rectangleshape",
                                    "link": "/patterns/creational/factory/rectangleshape"
                              },
                              {
                                    "text": "Shape",
                                    "link": "/patterns/creational/factory/shape"
                              },
                              {
                                    "text": "Shapefactory",
                                    "link": "/patterns/creational/factory/shapefactory"
                              }
                        ]
                  },
                  {
                        "text": "Prototype",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Employeeprototype",
                                    "link": "/patterns/creational/prototype/employeeprototype"
                              },
                              {
                                    "text": "Employeeprototypetest",
                                    "link": "/patterns/creational/prototype/employeeprototypetest"
                              },
                              {
                                    "text": "Prototypedemo",
                                    "link": "/patterns/creational/prototype/prototypedemo"
                              }
                        ]
                  },
                  {
                        "text": "Singleton",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Billpughsingleton",
                                    "link": "/patterns/creational/singleton/billpughsingleton"
                              },
                              {
                                    "text": "Clonablesingleton",
                                    "link": "/patterns/creational/singleton/clonablesingleton"
                              },
                              {
                                    "text": "Doublycheckedsingleton",
                                    "link": "/patterns/creational/singleton/doublycheckedsingleton"
                              },
                              {
                                    "text": "Eagerinitializedsingleton",
                                    "link": "/patterns/creational/singleton/eagerinitializedsingleton"
                              },
                              {
                                    "text": "Enumsingleton",
                                    "link": "/patterns/creational/singleton/enumsingleton"
                              },
                              {
                                    "text": "Enumsingletonimpl",
                                    "link": "/patterns/creational/singleton/enumsingletonimpl"
                              },
                              {
                                    "text": "Finalstaticsingleton",
                                    "link": "/patterns/creational/singleton/finalstaticsingleton"
                              },
                              {
                                    "text": "Serializedsingleton",
                                    "link": "/patterns/creational/singleton/serializedsingleton"
                              },
                              {
                                    "text": "Singletondemo",
                                    "link": "/patterns/creational/singleton/singletondemo"
                              },
                              {
                                    "text": "Singletondestroyerreflection",
                                    "link": "/patterns/creational/singleton/singletondestroyerreflection"
                              },
                              {
                                    "text": "Singletonserializedtest",
                                    "link": "/patterns/creational/singleton/singletonserializedtest"
                              },
                              {
                                    "text": "Staticblocksingleton",
                                    "link": "/patterns/creational/singleton/staticblocksingleton"
                              }
                        ]
                  }
            ]
      },
      {
            "text": "Structural Patterns",
            "collapsed": true,
            "items": [
                  {
                        "text": "Adapter",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Adapterdemo",
                                    "link": "/patterns/structural/adapter/adapterdemo"
                              },
                              {
                                    "text": "Car",
                                    "link": "/patterns/structural/adapter/car"
                              },
                              {
                                    "text": "Economyengine",
                                    "link": "/patterns/structural/adapter/economyengine"
                              },
                              {
                                    "text": "Engine",
                                    "link": "/patterns/structural/adapter/engine"
                              },
                              {
                                    "text": "Europeansocket",
                                    "link": "/patterns/structural/adapter/europeansocket"
                              },
                              {
                                    "text": "Europeansocketimpl",
                                    "link": "/patterns/structural/adapter/europeansocketimpl"
                              },
                              {
                                    "text": "Highperformanceengine",
                                    "link": "/patterns/structural/adapter/highperformanceengine"
                              },
                              {
                                    "text": "Mediumperformanceengine",
                                    "link": "/patterns/structural/adapter/mediumperformanceengine"
                              },
                              {
                                    "text": "Twinturboengine",
                                    "link": "/patterns/structural/adapter/twinturboengine"
                              },
                              {
                                    "text": "Usasocketadapter",
                                    "link": "/patterns/structural/adapter/usasocketadapter"
                              },
                              {
                                    "text": "Usasocketadapterimpl",
                                    "link": "/patterns/structural/adapter/usasocketadapterimpl"
                              }
                        ]
                  },
                  {
                        "text": "Bridge",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Animal",
                                    "link": "/patterns/structural/bridge/animal"
                              },
                              {
                                    "text": "Bananafeeder",
                                    "link": "/patterns/structural/bridge/bananafeeder"
                              },
                              {
                                    "text": "Bridgedemo",
                                    "link": "/patterns/structural/bridge/bridgedemo"
                              },
                              {
                                    "text": "Client",
                                    "link": "/patterns/structural/bridge/client"
                              },
                              {
                                    "text": "Device",
                                    "link": "/patterns/structural/bridge/device"
                              },
                              {
                                    "text": "Feeding",
                                    "link": "/patterns/structural/bridge/feeding"
                              },
                              {
                                    "text": "Monkey",
                                    "link": "/patterns/structural/bridge/monkey"
                              },
                              {
                                    "text": "Radio",
                                    "link": "/patterns/structural/bridge/radio"
                              },
                              {
                                    "text": "Radioremote",
                                    "link": "/patterns/structural/bridge/radioremote"
                              },
                              {
                                    "text": "Remotecontrol",
                                    "link": "/patterns/structural/bridge/remotecontrol"
                              },
                              {
                                    "text": "Tv",
                                    "link": "/patterns/structural/bridge/tv"
                              },
                              {
                                    "text": "Tvremote",
                                    "link": "/patterns/structural/bridge/tvremote"
                              }
                        ]
                  },
                  {
                        "text": "Composite",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Compositedemo",
                                    "link": "/patterns/structural/composite/compositedemo"
                              },
                              {
                                    "text": "File",
                                    "link": "/patterns/structural/composite/file"
                              },
                              {
                                    "text": "Filesystemcomponent",
                                    "link": "/patterns/structural/composite/filesystemcomponent"
                              },
                              {
                                    "text": "Folder",
                                    "link": "/patterns/structural/composite/folder"
                              }
                        ]
                  },
                  {
                        "text": "Decorator",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Coffee",
                                    "link": "/patterns/structural/decorator/coffee"
                              },
                              {
                                    "text": "Coffeedecorator",
                                    "link": "/patterns/structural/decorator/coffeedecorator"
                              },
                              {
                                    "text": "Decoratordemo",
                                    "link": "/patterns/structural/decorator/decoratordemo"
                              },
                              {
                                    "text": "Milkdecorator",
                                    "link": "/patterns/structural/decorator/milkdecorator"
                              },
                              {
                                    "text": "Simplecoffee",
                                    "link": "/patterns/structural/decorator/simplecoffee"
                              },
                              {
                                    "text": "Sugardecorator",
                                    "link": "/patterns/structural/decorator/sugardecorator"
                              }
                        ]
                  },
                  {
                        "text": "Facade",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Bookfacade",
                                    "link": "/patterns/structural/facade/bookfacade"
                              },
                              {
                                    "text": "Client",
                                    "link": "/patterns/structural/facade/client"
                              },
                              {
                                    "text": "Dvdplayer",
                                    "link": "/patterns/structural/facade/dvdplayer"
                              },
                              {
                                    "text": "Facadedemo",
                                    "link": "/patterns/structural/facade/facadedemo"
                              },
                              {
                                    "text": "Hometheaterfacade",
                                    "link": "/patterns/structural/facade/hometheaterfacade"
                              },
                              {
                                    "text": "Projector",
                                    "link": "/patterns/structural/facade/projector"
                              },
                              {
                                    "text": "Soundsystem",
                                    "link": "/patterns/structural/facade/soundsystem"
                              }
                        ]
                  },
                  {
                        "text": "Flyweight",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Flyweightdemo",
                                    "link": "/patterns/structural/flyweight/flyweightdemo"
                              },
                              {
                                    "text": "Oaktree",
                                    "link": "/patterns/structural/flyweight/oaktree"
                              },
                              {
                                    "text": "Pinetree",
                                    "link": "/patterns/structural/flyweight/pinetree"
                              },
                              {
                                    "text": "Tree",
                                    "link": "/patterns/structural/flyweight/tree"
                              },
                              {
                                    "text": "Treefactory",
                                    "link": "/patterns/structural/flyweight/treefactory"
                              }
                        ]
                  },
                  {
                        "text": "Proxy",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Internet",
                                    "link": "/patterns/structural/proxy/internet"
                              },
                              {
                                    "text": "Proxydemo",
                                    "link": "/patterns/structural/proxy/proxydemo"
                              },
                              {
                                    "text": "Proxyinternet",
                                    "link": "/patterns/structural/proxy/proxyinternet"
                              },
                              {
                                    "text": "Realinternet",
                                    "link": "/patterns/structural/proxy/realinternet"
                              }
                        ]
                  }
            ]
      },
      {
            "text": "Behavioral Patterns",
            "collapsed": true,
            "items": [
                  {
                        "text": "Chainofresponsibility",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Chainofresponsibilitydemo",
                                    "link": "/patterns/behavioral/chainofresponsibility/chainofresponsibilitydemo"
                              },
                              {
                                    "text": "Level1Support",
                                    "link": "/patterns/behavioral/chainofresponsibility/level1support"
                              },
                              {
                                    "text": "Level2Support",
                                    "link": "/patterns/behavioral/chainofresponsibility/level2support"
                              },
                              {
                                    "text": "Level3Support",
                                    "link": "/patterns/behavioral/chainofresponsibility/level3support"
                              },
                              {
                                    "text": "Supporthandler",
                                    "link": "/patterns/behavioral/chainofresponsibility/supporthandler"
                              }
                        ]
                  },
                  {
                        "text": "Command",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Command",
                                    "link": "/patterns/behavioral/command/command"
                              },
                              {
                                    "text": "Commanddemo",
                                    "link": "/patterns/behavioral/command/commanddemo"
                              },
                              {
                                    "text": "Light",
                                    "link": "/patterns/behavioral/command/light"
                              },
                              {
                                    "text": "Lightoffcommand",
                                    "link": "/patterns/behavioral/command/lightoffcommand"
                              },
                              {
                                    "text": "Lightoncommand",
                                    "link": "/patterns/behavioral/command/lightoncommand"
                              },
                              {
                                    "text": "Remotecontrol",
                                    "link": "/patterns/behavioral/command/remotecontrol"
                              }
                        ]
                  },
                  {
                        "text": "Interpreter",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Addexpression",
                                    "link": "/patterns/behavioral/interpreter/addexpression"
                              },
                              {
                                    "text": "Expression",
                                    "link": "/patterns/behavioral/interpreter/expression"
                              },
                              {
                                    "text": "Interpreterdemo",
                                    "link": "/patterns/behavioral/interpreter/interpreterdemo"
                              },
                              {
                                    "text": "Numberexpression",
                                    "link": "/patterns/behavioral/interpreter/numberexpression"
                              }
                        ]
                  },
                  {
                        "text": "Iterator",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Iterator",
                                    "link": "/patterns/behavioral/iterator/iterator"
                              },
                              {
                                    "text": "Iteratordemo",
                                    "link": "/patterns/behavioral/iterator/iteratordemo"
                              },
                              {
                                    "text": "Namerepository",
                                    "link": "/patterns/behavioral/iterator/namerepository"
                              }
                        ]
                  },
                  {
                        "text": "Mediator",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Chatroom",
                                    "link": "/patterns/behavioral/mediator/chatroom"
                              },
                              {
                                    "text": "Mediatordemo",
                                    "link": "/patterns/behavioral/mediator/mediatordemo"
                              },
                              {
                                    "text": "User",
                                    "link": "/patterns/behavioral/mediator/user"
                              }
                        ]
                  },
                  {
                        "text": "Memento",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Caretaker",
                                    "link": "/patterns/behavioral/memento/caretaker"
                              },
                              {
                                    "text": "Memento",
                                    "link": "/patterns/behavioral/memento/memento"
                              },
                              {
                                    "text": "Mementodemo",
                                    "link": "/patterns/behavioral/memento/mementodemo"
                              },
                              {
                                    "text": "Texteditor",
                                    "link": "/patterns/behavioral/memento/texteditor"
                              }
                        ]
                  },
                  {
                        "text": "Observer",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Cricketobserver",
                                    "link": "/patterns/behavioral/observer/cricketobserver"
                              },
                              {
                                    "text": "Cricketobserverdemo",
                                    "link": "/patterns/behavioral/observer/cricketobserverdemo"
                              },
                              {
                                    "text": "Cricketscore",
                                    "link": "/patterns/behavioral/observer/cricketscore"
                              },
                              {
                                    "text": "Observerchannelsony",
                                    "link": "/patterns/behavioral/observer/observerchannelsony"
                              },
                              {
                                    "text": "Observerchannelstar",
                                    "link": "/patterns/behavioral/observer/observerchannelstar"
                              },
                              {
                                    "text": "Observerchannelzee",
                                    "link": "/patterns/behavioral/observer/observerchannelzee"
                              }
                        ]
                  },
                  {
                        "text": "State",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Greenstate",
                                    "link": "/patterns/behavioral/state/greenstate"
                              },
                              {
                                    "text": "Redstate",
                                    "link": "/patterns/behavioral/state/redstate"
                              },
                              {
                                    "text": "State",
                                    "link": "/patterns/behavioral/state/state"
                              },
                              {
                                    "text": "Statedemo",
                                    "link": "/patterns/behavioral/state/statedemo"
                              },
                              {
                                    "text": "Trafficlight",
                                    "link": "/patterns/behavioral/state/trafficlight"
                              },
                              {
                                    "text": "Yellowstate",
                                    "link": "/patterns/behavioral/state/yellowstate"
                              }
                        ]
                  },
                  {
                        "text": "Strategy",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Creditcardpayment",
                                    "link": "/patterns/behavioral/strategy/creditcardpayment"
                              },
                              {
                                    "text": "Paymentcontext",
                                    "link": "/patterns/behavioral/strategy/paymentcontext"
                              },
                              {
                                    "text": "Paymentstrategy",
                                    "link": "/patterns/behavioral/strategy/paymentstrategy"
                              },
                              {
                                    "text": "Paypalpayment",
                                    "link": "/patterns/behavioral/strategy/paypalpayment"
                              },
                              {
                                    "text": "Strategydemo",
                                    "link": "/patterns/behavioral/strategy/strategydemo"
                              }
                        ]
                  },
                  {
                        "text": "Templatemethod",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Beverage",
                                    "link": "/patterns/behavioral/templatemethod/beverage"
                              },
                              {
                                    "text": "Coffee",
                                    "link": "/patterns/behavioral/templatemethod/coffee"
                              },
                              {
                                    "text": "Tea",
                                    "link": "/patterns/behavioral/templatemethod/tea"
                              },
                              {
                                    "text": "Templatemethoddemo",
                                    "link": "/patterns/behavioral/templatemethod/templatemethoddemo"
                              }
                        ]
                  },
                  {
                        "text": "Visitor",
                        "collapsed": true,
                        "items": [
                              {
                                    "text": "Book",
                                    "link": "/patterns/behavioral/visitor/book"
                              },
                              {
                                    "text": "Fruit",
                                    "link": "/patterns/behavioral/visitor/fruit"
                              },
                              {
                                    "text": "Itemelement",
                                    "link": "/patterns/behavioral/visitor/itemelement"
                              },
                              {
                                    "text": "Shoppingcartvisitor",
                                    "link": "/patterns/behavioral/visitor/shoppingcartvisitor"
                              },
                              {
                                    "text": "Shoppingcartvisitorimpl",
                                    "link": "/patterns/behavioral/visitor/shoppingcartvisitorimpl"
                              },
                              {
                                    "text": "Visitordemo",
                                    "link": "/patterns/behavioral/visitor/visitordemo"
                              }
                        ]
                  }
            ]
      },
      {
            "text": "Architecture Decision Records (ADRs)",
            "collapsed": true,
            "items": [
                  {
                        "text": "ADR 0001: JUnit 5 Jupiter",
                        "link": "/adr/0001-use-junit-5-jupiter"
                  },
                  {
                        "text": "ADR 0002: Package by Feature in LLD",
                        "link": "/adr/0002-package-by-feature-in-lld"
                  },
                  {
                        "text": "ADR 0003: Mermaid Diagram Standard",
                        "link": "/adr/0003-mermaid-diagram-standard"
                  }
            ]
      }
],
    socialLinks: [
      { icon: "github", link: "https://github.com/itsanurag-dev/design-handbook" }
    ],
    footer: {
      message: "Released under the MIT License.",
      copyright: "Copyright © 2026 Anurag Tiwari"
    }
  }
})
