package com.epam.rd.contactbook;

public class Contact {
    protected ContactInfo [] info = new ContactInfo[10];
    protected static String name;
    protected int startOfEmails=2;
    protected int startOfMedias=5;
    protected int withName=0;
    protected int withPhone =0;

    private class NameContactInfo implements ContactInfo{
        public NameContactInfo(String name){
            Contact.name=name;
        }
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }
    }

    public static class Email implements ContactInfo{
        private final String email;
        public Email (String emailAddress){
            this.email = emailAddress;
            }
        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo{

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String getValue() {
            return null;
        }
    }

    public Contact(String contactName) {
        if (contactName!= null || contactName != ""){
            info[0]=new NameContactInfo(contactName);
            withName++;
        }
    }


    public void rename(String newName) {
        name = (newName!=null && newName!="")? newName: name;
        info[0]=new NameContactInfo(name);
    }

    public Email addEmail(String localPart, String domain) {
        if (startOfEmails<5) {
            info[startOfEmails] = new Email(localPart + "@" + domain);
            return (Email) info[startOfEmails++];
        } else {return null;}
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if (startOfEmails<5) {
            info[startOfEmails] = new Email(firstname + "_" + lastname + "@epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            return (Email) info[startOfEmails++];
        } else {return null;}
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (info[1]==null){
            info[1] = new ContactInfo() {
            @Override
            public String getTitle() {
                        return "Tel";
                    }

            @Override
            public String getValue() {
                        return "+" + code + " " + number;
                    }
            };
            withPhone++;
            return info[1];
        } else {return null;}
    }

    public Social addTwitter(String twitterId) {
        if (startOfMedias < 10) {
            info[startOfMedias] = new Social() {
                @Override
                public String getTitle() {
                    return "Twitter";
                }

                @Override
                public String getValue() {
                    return twitterId;
                }
            };
            return (Social) info[startOfMedias++];
        } else {
            return null;
        }
    }
    public Social addInstagram(String instagramId) {
        if (startOfMedias<10){
            info[startOfMedias]=new Social() {
                @Override
                public String getTitle() {
                return "Instagram";
                }

                @Override
                public String getValue() {
                    return instagramId;
                }
            };
            return (Social) info[startOfMedias++];
        } else {
            return null;
        }
    }

    public Social addSocialMedia(String title, String id) {
        if (startOfMedias<10){
            info[startOfMedias]=new Social() {
                @Override
                public String getTitle() {
                    return title;
                }

                @Override
                public String getValue() {
                    return id;
                }
            };
            return (Social) info[startOfMedias++];
        }
        else {return null;}
    }

    public ContactInfo[] getInfo() {
        if (info==null){return null;}
        int q = withName+withPhone+(startOfEmails-2)+(startOfMedias-5);
        ContactInfo [] infoCopy = new ContactInfo[q];
        for (int i = 0, j=0; i<info.length;i++){
            if(info[i]!=null){
                infoCopy[j++]=info[i];
            }
        }
        return infoCopy;
    }

}
