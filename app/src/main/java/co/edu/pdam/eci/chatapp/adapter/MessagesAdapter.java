package co.edu.pdam.eci.chatapp.adapter;

/**
 * @author Santiago Carrillo
 */

public class MessagesAdapter
    /*extends RecyclerView.Adapter<MessagesAdapter.ViewHolder>*/
{

    /*
    private final Context context;

    private List<Message> messages = new ArrayList<>();

    public MessagesAdapter( Context context )
    {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
    {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.message_row, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position )
    {
        Message message = messages.get( position );
        viewHolder.sender.setText( message.getSender() );
        viewHolder.message.setText( message.getText() );
        if ( message.getImageUrl() != null )
        {
            viewHolder.sender.setVisibility( View.GONE );
            viewHolder.message.setVisibility( View.GONE );
            viewHolder.imageView.setVisibility( View.VISIBLE );
            Picasso.with( context ).load( message.getImageUrl() ).into( viewHolder.imageView );
        }
        else
        {
            viewHolder.sender.setVisibility( View.VISIBLE );
            viewHolder.message.setVisibility( View.VISIBLE );
        }
    }

    @Override
    public int getItemCount()
    {
        return messages.size();
    }

    public void addMessage( Message message )
    {
        messages.add( 0, message );
        notifyDataSetChanged();
    }

    public void removeMessage( Message message )
    {
        messages.remove( message );
        notifyDataSetChanged();
    }

    static class ViewHolder
        extends RecyclerView.ViewHolder
    {
        @Bind( R.id.sender )
        TextView sender;

        @Bind( R.id.message )
        TextView message;

        @Bind( R.id.image )
        ImageView imageView;

        ViewHolder( View view )
        {
            super( view );
            ButterKnife.bind( this, view );
        }
    }*/
}

